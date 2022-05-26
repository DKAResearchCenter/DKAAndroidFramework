package com.dkaresearchcenter.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel.AccountPanelFeature

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.dkaresearchcenter.dkaframework.R
import com.dkaresearchcenter.dkaframework.databinding.UiLayoutFunctionAuthManagescreenAccountPanelFeatureProductBinding

class DKAAuthAccountFeatureProduct : Fragment() {

    private lateinit var i : Intent
    private lateinit var mBinding : UiLayoutFunctionAuthManagescreenAccountPanelFeatureProductBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.ui_layout_function_auth_managescreen_account_panel_feature_product,
            container,
            false
        )

        mBinding.mGotoElektaApps.setOnClickListener {
            try {
                finishAffinity(requireActivity())
                i = requireActivity().packageManager.getLaunchIntentForPackage("yovanggaanandhika.product.politik")!!
                startActivity(i)
            }catch (e : Exception){
                Toast.makeText(requireActivity(), "Gagal Membuka Aplikasi : " + e, Toast.LENGTH_LONG).show()
            }
        }

        mBinding.mGoToIgoApps.setOnClickListener {
            try {
                finishAffinity(requireActivity())
                i = requireActivity().packageManager.getLaunchIntentForPackage("yovanggaanandhika.mitra.igo")!!
                startActivity(i)
            }catch (e : Exception){
                Toast.makeText(requireActivity(), "Gagal Membuka Aplikasi : " + e, Toast.LENGTH_LONG).show()
            }
        }
        return mBinding.root
    }
}