package com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel.AccountPanelFeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.yovanggaanandhika.dkaframework.R
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionAuthManagescreenAccountPanelFeatureRingkasanBinding

class DKAAuthAccountFeatureRingkasan : Fragment() {

    private lateinit var mBinding: UiLayoutFunctionAuthManagescreenAccountPanelFeatureRingkasanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.ui_layout_function_auth_managescreen_account_panel_feature_ringkasan, container, false)

        return mBinding.root
    }
}