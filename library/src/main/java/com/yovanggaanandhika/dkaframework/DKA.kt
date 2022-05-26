package com.yovanggaanandhika.dkaframework

import android.annotation.SuppressLint
import android.content.Context
import com.yovanggaanandhika.dkaframework.Feature.Notification.DKANotification
import com.yovanggaanandhika.dkaframework.Functions.Auth.DKAAuth

class DKA {

    /** Companion Object Initialition Global Variable Framework **/
    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var CONTEXT: Context

        fun initialize(context: Context) {
            this.CONTEXT = context
        }

        const val APPLICATION_NAME: String = "APPLICATION_NAME"
    }

    /** Library For Authentification **/
    class Auth : DKAAuth()

    /** Library For Notification **/
    class Notification : DKANotification()
}
