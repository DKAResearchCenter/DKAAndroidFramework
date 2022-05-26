package com.yovanggaanandhika.dkaframework.Interface

import android.content.Context
import android.graphics.Bitmap
import android.location.Location

class DKAOptions {

    interface Api {
        interface HTTP {
            interface LocationConnection {
                fun RealtimeLocation(location: Location)
                fun OnTimeLocation(location: Location)
            }
        }
    }

    interface Service {
        interface Component {
            interface DKALocationRequestUpdate {
                fun onCreateDKALocationUpdate(context: Context)
                fun onStartCommandDKALocationUpdate(context: Context)
                fun onDestroyLocationUpdate(context: Context)
            }

            interface DKACameraBackground {
                fun onCreateDKACameraBackground(context: Context)
                fun onStartCommandDKACameraBackground(context: Context)
                fun onDestroyDKACameraBackground(context: Context)
            }
        }
    }

    interface Auth {

        interface Account {

            interface ClassImplementation {
                fun addApplicationName(AppName: String): ClassImplementation
                fun build()
            }
        }

    }

    class Notification {

        interface ClassImplementation {
            fun addChannelId(text: String? = null, mNotificationName: String? = null, mDescNotification: String? = null): ClassImplementation
            fun addChannelId(text: String? = null): ClassImplementation
            fun addNotificationId(mId: Int? = null): ClassImplementation
            fun addSmallIcon(mDrawable: Int? = null): ClassImplementation
            fun addLargeIcon(mUrl: Bitmap? = null): ClassImplementation
            fun addTitle(mText: String? = null): ClassImplementation
            fun addText(mText: String? = null): ClassImplementation
            fun addGroup(mId: String?): ClassImplementation
            fun setAutoCancel(mAutoCancel: Boolean? = false): ClassImplementation
            fun addDirectActivity(mActivity: Class<*>? = null): ClassImplementation
            fun addPriority(mPriority: Int? = null): ClassImplementation
            fun addStyles(mBitmap: Bitmap? = null, mBigLargeIcon: Bitmap? = null): ClassImplementation
            fun addStyles(mBigText: String? = null): ClassImplementation
            fun addStyles(mInboxArray: ArrayList<String>? = null): ClassImplementation
            fun build(): android.app.Notification
        }
    }

    class Widget {

    }

}
