package com.dkaresearchcenter.dkaframework.Service.Component.Sync

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dkaresearchcenter.dkaframework.Architecture.Adapter.SyncAdapter.DKAAuthentificatorSyncAdaptor

class DKAAuthentificatorSyncService : Service() {

    companion object {
        private var sSyncAdapter : DKAAuthentificatorSyncAdaptor? = null
        private val sSyncAdaptorLock = Any()
    }
    override fun onCreate() {
        super.onCreate()

        /** Syncronitat **/
        synchronized(sSyncAdaptorLock){
            sSyncAdapter = sSyncAdapter ?: DKAAuthentificatorSyncAdaptor(applicationContext,
                autoInitialize = true,
                allowParallelSync = true
            )
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return sSyncAdapter?.syncAdapterBinder ?: throw IllegalStateException()
    }
}