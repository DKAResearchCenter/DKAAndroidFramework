package com.dkaresearchcenter.dkaframework.Functions.Message.Component.View

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dkaresearchcenter.dkaframework.R
import com.dkaresearchcenter.dkaframework.databinding.UiLayoutFunctionMessageManagescreenLayoutCallBinding


class DKAMsgScreenCall : AppCompatActivity(), SensorEventListener {

    private lateinit var rm : Ringtone

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    private lateinit var mBinding : UiLayoutFunctionMessageManagescreenLayoutCallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.ui_layout_function_message_managescreen_layout_call
        )

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)


        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        rm = RingtoneManager.getRingtone(this, uri)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            rm.setLooping(true)
        }


    }

    override fun onStart() {
        super.onStart()
        rm.play()
    }

    override fun onResume() {
        super.onResume()
        sensor?.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onStop() {
        super.onStop()
        rm.stop()
    }

    @SuppressLint("InvalidWakeLockTag")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onSensorChanged(event: SensorEvent?) {
        val distance = event?.values?.get(0)
        if (distance!! < 5){

        }else{

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}