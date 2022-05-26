package com.dkaresearchcenter.dkaframework.Api.HTTP

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.dkaresearchcenter.dkaframework.R
import java.io.File

class CameraConnection(var context: Context) {

    fun imageTaken(file : File){
        AndroidNetworking.upload( context.resources.getString(R.string.app_host) + "/upload")
            .addMultipartFile("GAMBAR", file)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {

                }

                override fun onError(anError: ANError?) {

                }

            })
    }
}