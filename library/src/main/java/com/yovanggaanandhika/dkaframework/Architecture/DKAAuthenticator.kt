package com.yovanggaanandhika.dkaframework.Architecture

import android.accounts.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.yovanggaanandhika.dkaframework.DKA
import com.yovanggaanandhika.dkaframework.Functions.Auth.Component.View.DKAAuthLogin

internal class DKAAuthenticator(var context: Context) : AbstractAccountAuthenticator(context) {

    /** Declaration Variable This Class Authentificator Data  **/
    private var bundle: Bundle = Bundle()
    private var i: Intent = Intent()
    /** End Declaration Variable This Class Authentificator Data **/

    // Editing properties is not supported
    /** Function Impelementation For AbstractAccountAuthenticator Edit Text **/
    @Throws(NetworkErrorException::class)
    override fun editProperties(r: AccountAuthenticatorResponse, s: String): Bundle {
        throw UnsupportedOperationException()
    }

    @Throws(NetworkErrorException::class)
    override fun addAccount(response: AccountAuthenticatorResponse?, accountType: String?, authTokenType: String?, requiredFeatures: Array<out String>?, options: Bundle?): Bundle {
        i = Intent(context, DKAAuthLogin::class.java)
        /** Edit Put Extra If Add Account Add Account AM (Account Manager) **/
        i.putExtra(DKAAuthLogin.ARG_KEY_ACCOUNT_TYPE, accountType)
        i.putExtra(DKAAuthLogin.ARG_KEY_AUTHTOKEN_TYPE, authTokenType)
        i.putExtra(DKAAuthLogin.ARG_IS_ADDING_NEW_ACCOUNT, true)
        i.putExtra(AccountManager.KEY_ACCOUNT_MANAGER_RESPONSE, response)

        return options?.apply {
            i.putExtra(DKA.APPLICATION_NAME, options.getString(DKA.APPLICATION_NAME))
            putParcelable(AccountManager.KEY_INTENT, i)
        } ?: bundle.apply {
            putParcelable(AccountManager.KEY_INTENT, i)
        }
    }


    // Ignore attempts to confirm credentials
    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(
            r: AccountAuthenticatorResponse,
            account: Account,
            bundle: Bundle
    ): Bundle? = null


    // Getting an authentication token is not supported
    @Throws(NetworkErrorException::class)
    override fun getAuthToken(
            r: AccountAuthenticatorResponse,
            account: Account,
            s: String,
            bundle: Bundle
    ): Bundle {
        throw UnsupportedOperationException()
    }

    // Getting a label for the auth token is not supported
    override fun getAuthTokenLabel(s: String): String {
        throw UnsupportedOperationException()
    }

    // Updating user credentials is not supported
    @Throws(NetworkErrorException::class)
    override fun updateCredentials(
            r: AccountAuthenticatorResponse,
            account: Account,
            s: String,
            bundle: Bundle
    ): Bundle {
        throw UnsupportedOperationException()
    }

    // Checking features for the account is not supported
    @Throws(NetworkErrorException::class)
    override fun hasFeatures(
            r: AccountAuthenticatorResponse,
            account: Account,
            strings: Array<String>
    ): Bundle {
        throw UnsupportedOperationException()
    }
}