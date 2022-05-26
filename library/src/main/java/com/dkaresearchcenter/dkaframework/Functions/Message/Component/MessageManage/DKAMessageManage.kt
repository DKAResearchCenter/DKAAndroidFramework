@file:Suppress("DEPRECATION")

package com.dkaresearchcenter.dkaframework.Functions.Message.Component.MessageManage

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.dkaresearchcenter.dkaframework.Functions.Auth.Component.AuthManage.DKAAuthManage
import com.dkaresearchcenter.dkaframework.Functions.Message.Component.View.Component.MessageLayoutCall
import com.dkaresearchcenter.dkaframework.Functions.Message.Component.View.Component.MessageLayoutChat
import com.dkaresearchcenter.dkaframework.Functions.Message.Component.View.Component.MessageLayoutGroup
import com.dkaresearchcenter.dkaframework.R
import com.dkaresearchcenter.dkaframework.databinding.UiLayoutFunctionMessageManagescreenBinding

class DKAMessageManage : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var i : Intent
    private lateinit var mBinding : UiLayoutFunctionMessageManagescreenBinding
    private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.ui_layout_function_message_managescreen)

        mFragmentManager = supportFragmentManager
        mBinding.mBottomNav.setOnNavigationItemSelectedListener(this)

        /*val badge = mBinding.mBottomNav.getOrCreateBadge(R.id.mMsgSystem)?.apply {
            isVisible = true
            number = 100
            backgroundColor = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) resources.getColor(R.color.colorPrimary, theme) else resources.getColor(R.color.colorPrimary)
        }*/

        mBinding.mBottomNav

        mFragmentManager.beginTransaction()
            .replace(mBinding.mContentContainer.id, MessageLayoutChat())
            .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mIntegration -> {
                mFragmentManager.beginTransaction()
                    .replace(mBinding.mContentContainer.id, MessageLayoutChat())
                    .commit()
                true
            }
            R.id.mMsgSystem -> {
                mFragmentManager.beginTransaction()
                    .replace(mBinding.mContentContainer.id, MessageLayoutChat())
                    .commit()
                true
            }
            R.id.mGroup -> {
                mFragmentManager.beginTransaction()
                    .replace(mBinding.mContentContainer.id, MessageLayoutGroup())
                    .commit()
                true
            }
            R.id.mCall -> {
                mFragmentManager.beginTransaction()
                    .replace(mBinding.mContentContainer.id, MessageLayoutCall())
                    .commit()

                true
            }
            R.id.mAccountBadge -> {
                i = Intent(this, DKAAuthManage::class.java)
                startActivity(i)
                false
            }
            else -> {
                i = Intent(this, DKAAuthManage::class.java)
                startActivity(i)
                false
            }
        }
    }

}