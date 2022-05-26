package com.yovanggaanandhika.dkaframework.Service.Component.Auth

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.yovanggaanandhika.dkaframework.Architecture.DKAAuthenticator

class DKAAuthentificatorService : Service() {

    // Instance field that stores the dka_auth_accountauthenticator object

    override fun onCreate() {
        super.onCreate()

    }
    override fun onBind(intent: Intent?): IBinder? = DKAAuthenticator(this).iBinder

}