package com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yovanggaanandhika.dkaframework.DKA
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionAuthManagescreenAccountEmptyBinding

internal class DKAAuthAccountNotFound() : Fragment() {

    private lateinit var mBinding : UiLayoutFunctionAuthManagescreenAccountEmptyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        mBinding = UiLayoutFunctionAuthManagescreenAccountEmptyBinding.inflate(inflater, container, false)

        mBinding.mButtonToLogin.setOnClickListener {
            DKA.Auth()
                    .addApplicationName(DKA.CONTEXT.applicationInfo.loadLabel(DKA.CONTEXT.packageManager).toString())
                    .build()
        }

        return mBinding.root
    }
}