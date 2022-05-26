@file:Suppress("DEPRECATION")

package com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel.AccountPanelFeature.DKAAuthAccountFeatureLanggananPembayaran
import com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel.AccountPanelFeature.DKAAuthAccountFeatureProduct
import com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel.AccountPanelFeature.DKAAuthAccountFeatureRingkasan
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionAuthManagescreenAccountPanelBinding

internal class DKAAuthAccountPanel(var mActivity: Activity) : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var fm : FragmentManager
    private lateinit var mBinding : UiLayoutFunctionAuthManagescreenAccountPanelBinding
    private var mFeatureList : MutableList<FeatureDKAAuthManage> = mutableListOf()

    private data class FeatureDKAAuthManage(
            val TAB_NAME : String?,
            val FRAGMENT : Fragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = UiLayoutFunctionAuthManagescreenAccountPanelBinding.inflate(inflater, container, false)

        fm = childFragmentManager

        /** Add **/
        mBinding.mShowDialogSelect.setOnClickListener {
            DKAAuthAccountPanelAccountSwitcherDialog(
                mActivity
            )
                    .show(fm, "")
        }
        /** End Of Listeners For Click Listeners**/

        mFeatureList.clear()
        mFeatureList.add(FeatureDKAAuthManage("Ringkasan Akun", DKAAuthAccountFeatureRingkasan()))
        mFeatureList.add(FeatureDKAAuthManage("informasi Personal", DKAAuthAccountFeatureRingkasan()))
        mFeatureList.add(FeatureDKAAuthManage("Sekuritas", DKAAuthAccountFeatureRingkasan()))
        mFeatureList.add(FeatureDKAAuthManage("Pembayaran & Langganan", DKAAuthAccountFeatureLanggananPembayaran()))
        mFeatureList.add(FeatureDKAAuthManage("Produk", DKAAuthAccountFeatureProduct()))

        mBinding.mContentDKAAuthFeature.adapter = FeatureDKAAuthManageAdapter(childFragmentManager)
        mBinding.mFeature.setupWithViewPager(mBinding.mContentDKAAuthFeature)

        //mBinding.mBottomBarLayoutViewAccount.setOnNavigationItemSelectedListener(this)

        mBinding.mBackActivity.setOnClickListener {
            requireActivity().finish()
        }
        return mBinding.root
    }

    @Suppress("DEPRECATION")
    private inner class FeatureDKAAuthManageAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){

        override fun getItem(position: Int): Fragment = mFeatureList[position].FRAGMENT

        override fun getCount(): Int = mFeatureList.size

        override fun getPageTitle(position: Int): CharSequence? {
            return mFeatureList[position].TAB_NAME
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }


}