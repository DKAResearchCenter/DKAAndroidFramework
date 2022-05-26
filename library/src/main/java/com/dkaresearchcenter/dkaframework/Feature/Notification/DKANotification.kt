package com.dkaresearchcenter.dkaframework.Feature.Notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dkaresearchcenter.dkaframework.DKA
import com.dkaresearchcenter.dkaframework.DKA.Notification
import com.dkaresearchcenter.dkaframework.Interface.DKAMaster
import com.dkaresearchcenter.dkaframework.Interface.DKAOptions

open class DKANotification : DKAMaster<Notification>(), DKAOptions.Notification.ClassImplementation {

    data class DataPass (var Key: String? = null, var Values : Any? = null) {

        constructor(Key: String?, Values: Int?) : this() {
            this.Key = Key
            this.Values = Values
        }

        constructor(Key: String?, Values: String?) : this() {
            this.Key = Key
            this.Values = Values
        }

        constructor(Key: String?, Values: Boolean?) : this() {
            this.Key = Key
            this.Values = Values
        }

        constructor(Key: String?, Values : Float) : this() {
            this.Key = Key
            this.Values = Values
        }

        constructor(Key: String?, Values : Byte) : this() {
            this.Key = Key
            this.Values = Values
        }
    }

    companion object {
        val NOTIFICATION_STYLE_BIG_PICTURE : Int? = 30001
        val NOTIFICATION_STYLE_BIG_TEXT : Int? = 30002
        val NOTIFICATION_STYLE_INBOX_TEXT : Int? = 30003
        val NOTIFICATION_STYLE_MESSAGE_TEXT : Int? = 30004
        val NOTIFICATION_STYLE_MEDIA_CONTROL : Int? = 30005
    }

    private lateinit var i : Intent
    private lateinit var DKABuilder : NotificationCompat.Builder
    private lateinit var mPendingIntent : PendingIntent

    private var mChanneID : String? = null
    private var mId : Int? = null
    private var mDirectClass : Class<*>? = null
    private var mDrawable : Int? = null
    private var mLargeDrawable : Bitmap? = null
    private var mTitle : String? = null
    private var mText : String? = null
    private var mGroupId : String? = null
    private var mPriority : Int? = NotificationCompat.PRIORITY_DEFAULT
    private var mDataExtract : ArrayList<DataPass>? = arrayListOf()
    private var mStyle : Boolean = false
    private var mStyleType : NotificationCompat.Style? = null
    private var mAutoCancel : Boolean = false

    override fun addChannelId(text: String?, mNotificationName: String?, mDescNotification: String?): DKAOptions.Notification.ClassImplementation {
        if (mNotificationName != null){
            val notificationManager: NotificationManager = DKA.CONTEXT.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(text, mNotificationName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = mDescNotification
                }

                notificationManager.createNotificationChannel(channel)
            }
        }

        mChanneID = text

        return this
    }

    override fun addChannelId(text: String?): DKAOptions.Notification.ClassImplementation {
        mChanneID = text
        return this
    }

    override fun addNotificationId(mId: Int?): DKAOptions.Notification.ClassImplementation {
        this.mId = mId

        return this
    }

    override fun addSmallIcon(mDrawable: Int?): DKAOptions.Notification.ClassImplementation {
        this.mDrawable = mDrawable
        return this
    }

    override fun addLargeIcon(mUrl : Bitmap?): DKAOptions.Notification.ClassImplementation {
        mLargeDrawable = mUrl
        return this
    }

    override fun addTitle(mText: String?): DKAOptions.Notification.ClassImplementation {

        mTitle = mText

        return this
    }

    override fun addText(mText: String?): DKAOptions.Notification.ClassImplementation {
        this.mText = mText
        return this
    }

    override fun addGroup(mId: String?): DKAOptions.Notification.ClassImplementation {
        this.mGroupId = mId
        return this
    }


    override fun setAutoCancel(mAutoCancel: Boolean?): DKAOptions.Notification.ClassImplementation {
        this.mAutoCancel = mAutoCancel ?: false
        return this
    }

    override fun addDirectActivity(mActivity: Class<*>?): DKAOptions.Notification.ClassImplementation {
        mDirectClass = mActivity
        return this
    }

    override fun addPriority(mPriority: Int?): DKAOptions.Notification.ClassImplementation {
        this.mPriority = mPriority
        return this
    }

    override fun addStyles(mBitmap: Bitmap?, mBigLargeIcon: Bitmap?): DKAOptions.Notification.ClassImplementation {
        mStyle = true
        mStyleType = NotificationCompat.BigPictureStyle()
        if (mBitmap != null) (mStyleType as NotificationCompat.BigPictureStyle).bigPicture(mBitmap)
        if (mBigLargeIcon != null) (mStyleType as NotificationCompat.BigPictureStyle).bigLargeIcon(mBigLargeIcon) ?: (mStyleType as NotificationCompat.BigPictureStyle).bigLargeIcon(mBigLargeIcon)

        return this
    }

    override fun addStyles(mBigText: String?): DKAOptions.Notification.ClassImplementation {
        mStyle = true
        mStyleType = NotificationCompat.BigTextStyle()
        if (mBigText != null) (mStyleType as NotificationCompat.BigTextStyle).bigText(mBigText)
        return this
    }

    override fun addStyles(mInboxArray: ArrayList<String>?): DKAOptions.Notification.ClassImplementation {
        mStyle = true
        mStyleType = NotificationCompat.InboxStyle()
        var num = 0

        do {
            (mStyleType as NotificationCompat.InboxStyle).addLine(mInboxArray!![num])
            num++
        }while (mInboxArray!!.size < num)

        return this
    }

    override fun build() : android.app.Notification {

        DKABuilder = NotificationCompat.Builder(DKA.CONTEXT, mChanneID!!)

        DKABuilder.apply {

            if (mDrawable != null){
                setSmallIcon(mDrawable!!)
            }

            if (mLargeDrawable != null){
                setLargeIcon(mLargeDrawable)
            }

            if (mTitle != null){
                setContentTitle(mTitle)
            }
            if (mText != null) {
                setContentTitle(mText)
            }
            if (mDirectClass != null){
                i = Intent(DKA.CONTEXT, mDirectClass).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    if (mDataExtract!!.size > 0){
                        var num = 0
                        do {
                            try {
                                putExtra(mDataExtract!![num].Key, mDataExtract!![num].Values as? String )
                            }catch (e : Exception){}
                            num++
                        }while (mDataExtract!!.size < num)
                    }
                }
                mPendingIntent = PendingIntent.getActivity(DKA.CONTEXT, 0, i,0)
                setContentIntent(mPendingIntent)
            }

            if (mPriority != null){
                priority = mPriority!!
            }
            if (mStyle){
                setStyle(mStyleType)
            }

            if (mGroupId != null){
                setGroup(mGroupId)
            }

            if (!mAutoCancel){
                setAutoCancel(mAutoCancel)
            }

        }


        return DKABuilder.build()
    }


}