package com.dkaresearchcenter.dkaframework.Architecture.Adapter.SyncAdapter

import android.accounts.Account
import android.content.*
import android.os.Bundle

class DKAAuthentificatorSyncAdaptor constructor(
    context : Context,
    autoInitialize : Boolean,
    allowParallelSync : Boolean = false,
    val mContentResolver: ContentResolver = context.contentResolver
) : AbstractThreadedSyncAdapter(context, autoInitialize, allowParallelSync) {

    override fun onPerformSync(account: Account?, extras: Bundle?, authority: String?, provider: ContentProviderClient?, syncResult: SyncResult?) {

    }

}