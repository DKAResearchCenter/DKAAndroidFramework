package com.dkaresearchcenter.dkaframework.Functions.Auth

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import android.os.Bundle
import com.dkaresearchcenter.dkaframework.DKA
import com.dkaresearchcenter.dkaframework.Interface.DKAOptions
import com.dkaresearchcenter.dkaframework.R

open class DKAAuth : DKAOptions.Auth.Account.ClassImplementation {

    /***
     * Global Private Variable Declaration For The Application Data
     * ALL RESERVED
     */

    private var bundle : Bundle = Bundle()
    private var am : AccountManager = AccountManager.get(DKA.CONTEXT)
    private var applicationName : String? = null

    data class DKAAccount(
        var NAME : String?,
        var TYPE : String?
    )

    var getAccountList : MutableList<Account> = am.getAccountsByType(DKA.CONTEXT.resources.getString(R.string.dka_account_type)).toMutableList()
    //var getDefaultAccount : Account? = am.getAccountsByType(context.resources.getString(R.string.dka_account_type))[0]


    override fun addApplicationName(AppName : String): DKAOptions.Auth.Account.ClassImplementation {
        applicationName = AppName
        return this
    }

    override fun build() {
        if (applicationName != null){
            bundle.putString(DKA.APPLICATION_NAME, applicationName)
        }
        am.addAccount(DKA.CONTEXT.resources.getString(R.string.dka_account_type), null, null, bundle, DKA.CONTEXT as Activity, null, null)
    }
}