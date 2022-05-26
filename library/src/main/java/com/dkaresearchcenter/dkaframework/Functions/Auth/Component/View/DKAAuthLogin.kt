@file:Suppress("DEPRECATION")

package com.dkaresearchcenter.dkaframework.Functions.Auth.Component.View

import android.accounts.Account
import android.accounts.AccountAuthenticatorActivity
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.databinding.DataBindingUtil
import com.dkaresearchcenter.dkaframework.DKA
import com.dkaresearchcenter.dkaframework.R
import com.dkaresearchcenter.dkaframework.databinding.UiLayoutFunctionAuthNewBinding
import java.util.*

@Suppress("DEPRECATION")
class DKAAuthLogin : AccountAuthenticatorActivity() {

    private var i: Intent = Intent()
    private lateinit var mBinding: UiLayoutFunctionAuthNewBinding
    private lateinit var mAccountManager: AccountManager

    private var applicationName: String? = null
    private var accountType: String? = null
    private var tokentAuthType: String? = null

    companion object {
        val ARG_KEY_ACCOUNT_TYPE: String = "AKUN_TYPE"
        val ARG_KEY_AUTHTOKEN_TYPE: String = "TOKEN_TYPE"

        val ARG_PASSWORD_DATA: String = "password_data"
        val ARG_IS_ADDING_NEW_ACCOUNT: String = "account_adding_new"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        mAccountManager = AccountManager.get(baseContext)

        applicationName = intent.getStringExtra(DKA.APPLICATION_NAME)
        accountType = intent.getStringExtra(ARG_KEY_ACCOUNT_TYPE) ?: "account.dka"
        tokentAuthType = intent.getStringExtra(ARG_KEY_ACCOUNT_TYPE) ?: ""

        mBinding = DataBindingUtil.setContentView(this, R.layout.ui_layout_function_auth_new)

        mBinding.mLogoDKA.apply {
            setImageResource(R.drawable.assets_framework_dka)
        }

        mBinding.GoToRegisterScreen.setOnClickListener {
            i = Intent(this, DKAAuthRegister::class.java)
            startActivity(i)
        }

        mBinding.GoToForgetPass.setOnClickListener {

        }

        mBinding.mSubmitLoginDKA.setOnClickListener {
            LoginAuth().execute()
        }

        mBinding.mDKALoginBackButton.visibility = View.GONE
        mBinding.mDKALoginBackButton.setOnClickListener {
            finish()
        }

        if (applicationName != null) {
            mBinding.mDKALoginBackButton.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mBinding.mDescLoginApp.text = Html.fromHtml("${resources.getString(R.string.dka_ui_activity_authentification_authscreen_welcomedesc_reg)} <b>${applicationName}</b>", Html.FROM_HTML_MODE_COMPACT)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    @SuppressLint("StaticFieldLeak")
    private inner class LoginAuth : AsyncTask<Void, Void, Intent>() {
        private var UserName = mBinding.mLoginFormUsername.text.toString()
        private var PassName = mBinding.mLoginFormPassword.text.toString()

        override fun doInBackground(vararg params: Void?): Intent {
            i.putExtra(AccountManager.KEY_ACCOUNT_NAME, UserName)
            i.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
            i.putExtra(AccountManager.KEY_AUTHTOKEN, tokentAuthType)
            i.putExtra(ARG_PASSWORD_DATA, PassName)
            return i
        }

        override fun onPostExecute(result: Intent?) {
            finishLogin(result!!)
        }

    }

    override fun getSharedPreferences(name: String?, mode: Int): SharedPreferences {


        return super.getSharedPreferences(name, mode)
    }

    private fun finishLogin(i: Intent) {
        val accountUser = i.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
        val accoutPass = i.getStringExtra(ARG_PASSWORD_DATA)
        val accountType = i.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE)

        val account = Account(accountUser, accountType)

        if (intent.getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {
            mAccountManager.apply {
                addAccountExplicitly(account, accoutPass, null)
                setUserData(account, "TIME_REGISTER", Calendar.getInstance().timeInMillis.toString())
                setUserData(account, "IMAGE_PROFILE", "https://scontent.fupg3-1.fna.fbcdn.net/v/t1.0-9/s960x960/79371896_783084398785806_4300302908102017024_o.jpg?_nc_cat=106&_nc_eui2=AeFxqhZWqKMZ8DgzlgG1U8Eg6XrsRJsYfJBb3FP7dOfIk_t0Dk7txaIgsF9Dp1j2FdhV-owEb15nqH2C-xcYHGNMcGYqEMNTV1hsciS6x-EATA&_nc_oc=AQmRvo1_etMWN7mu8ssLGaJDlwKxTUa9qwakyzp3wG_lkm40Q_KiMxchGI_NnaUg3aA&_nc_ht=scontent.fupg3-1.fna&_nc_tp=7&oh=a3994d89090bc584bb4727a9e05231a7&oe=5EB5952B")
            }
        } else {
            mAccountManager.setPassword(account, accoutPass)
        }

        setAccountAuthenticatorResult(i.extras)
        setResult(Activity.RESULT_OK, i)
        finish()
    }

    override fun finish() {
        super.finish()
    }
}