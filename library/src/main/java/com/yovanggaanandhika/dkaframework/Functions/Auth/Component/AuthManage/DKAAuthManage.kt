package com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yovanggaanandhika.dkaframework.DKA
import com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel.DKAAuthAccountPanel
import com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.AccountPanel.DKAAuthAccountSearch
import com.yovanggaanandhika.dkaframework.Functions.Auth.Component.AuthManage.AuthManageComponent.DKAAuthAccountNotFound
import com.yovanggaanandhika.dkaframework.Functions.Message.Component.MessageManage.DKAMessageManage
import com.yovanggaanandhika.dkaframework.R
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionAuthManagescreenBinding

@Suppress("DEPRECATION")
class DKAAuthManage : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var i : Intent = Intent()
    private lateinit var mFragmentManager : FragmentManager
    private lateinit var mBinding : UiLayoutFunctionAuthManagescreenBinding

    /** On Create If Create Activity In DKA Auth Manage  **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            with(window) {
                requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                enterTransition = Fade()
            }
        }
        /** add Data Binding For Data Binding Utils **/
        mBinding = DataBindingUtil.setContentView(this, R.layout.ui_layout_function_auth_managescreen)

        mFragmentManager = supportFragmentManager
        mBinding.mBottomBarLayoutViewAccount.setOnNavigationItemSelectedListener(this)

    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onStart() {
        super.onStart()
        CheckingAccountState()
    }

    override fun onResume() {
        super.onResume()
        CheckingAccountState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mAccountBadge -> {
                CheckingAccountState()
                true
            }
            R.id.mSearchTelusur -> {
                mFragmentManager
                    .beginTransaction()
                    .replace(R.id.mFragmentContent,
                        DKAAuthAccountSearch()
                    )
                    .commit()
                true
            }
            R.id.mMsgSystem -> {
                i = Intent(this@DKAAuthManage, DKAMessageManage::class.java)
                startActivity(i)
                false
            }
            else -> {
                false
            }
        }
    }

    private fun CheckingAccountState(){
        if ( DKA.Auth().getAccountList.size > 0){
            mFragmentManager
                .beginTransaction()
                .replace(R.id.mFragmentContent,
                    DKAAuthAccountPanel(this)
                )
                .commit()
        }else{
            mFragmentManager
                .beginTransaction()
                .replace(R.id.mFragmentContent, DKAAuthAccountNotFound())
                .commit()
        }
    }

}