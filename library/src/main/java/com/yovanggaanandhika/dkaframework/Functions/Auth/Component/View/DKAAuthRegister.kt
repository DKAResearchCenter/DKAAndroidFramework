package com.yovanggaanandhika.dkaframework.Functions.Auth.Component.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yovanggaanandhika.dkaframework.R
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionAuthRegisterBinding

class DKAAuthRegister : AppCompatActivity() {

    @Suppress("DEPRECATION")
    private var handler : Handler = Handler()
    private var bundle : Bundle = Bundle()
    private lateinit var mBinding : UiLayoutFunctionAuthRegisterBinding
    private var i : Intent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.ui_layout_function_auth_register)
    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onRestart() {
        super.onRestart()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onLowMemory() {
        super.onLowMemory()

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }




}