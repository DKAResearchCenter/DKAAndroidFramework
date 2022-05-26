package com.dkaresearchcenter.dkaframework

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.multidex.MultiDex
import com.androidnetworking.AndroidNetworking
import com.google.firebase.analytics.FirebaseAnalytics

abstract class DKAApplication : Application() {

    private lateinit var mFirebaseAnalytics : FirebaseAnalytics
    private lateinit var i : Intent

    
    override fun attachBaseContext(base: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(base)
    }

    @SuppressLint("WorldWriteableFiles")
    override fun onCreate() {
        /** @TODO Get Context**/
        DKA.initialize(applicationContext)
        /** @TODO Android Networking For Connection **/
        AndroidNetworking.initialize(applicationContext)

        /*Fabric.with(applicationContext, Crashlytics.Builder().core(mCrashlytics).build())*/
        super.onCreate()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }


}