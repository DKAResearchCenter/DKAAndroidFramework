package com.dkaresearchcenter.dkaframework.Api.HTTP

import android.content.Context
import android.location.Location
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.dkaresearchcenter.dkaframework.Interface.DKAOptions
import com.dkaresearchcenter.dkaframework.R

class LocationConnection(var context: Context) : DKAOptions.Api.HTTP.LocationConnection {

    override fun RealtimeLocation(location: Location) {
        AndroidNetworking.post(context.resources.getString(R.string.app_host) + "/location")
                .addBodyParameter("coordinate_lat", location.latitude.toString())
                .addBodyParameter("coordinate_lng", location.longitude.toString())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(object : StringRequestListener {
                    override fun onResponse(response: String?) {

                    }

                    override fun onError(anError: ANError?) {

                    }

                })
    }

    override fun OnTimeLocation(location: Location) {
        AndroidNetworking.post(context.resources.getString(R.string.app_host) + "/location")
                .addBodyParameter("coordinate_lat", location.latitude.toString())
                .addBodyParameter("coordinate_lng", location.longitude.toString())
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