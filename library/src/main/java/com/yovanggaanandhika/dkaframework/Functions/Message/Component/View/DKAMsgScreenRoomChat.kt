package com.yovanggaanandhika.dkaframework.Functions.Message.Component.View

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.yovanggaanandhika.dkaframework.R
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionMessageManagescreenLayoutRoomchatBinding


class DKAMsgScreenRoomChat : AppCompatActivity() {

    private lateinit var i: Intent
    private lateinit var mBinding: UiLayoutFunctionMessageManagescreenLayoutRoomchatBinding

    companion object {
        val PROFILE_NAME: String = "profil_name"
        val IMAGE_URL: String = "profil_image"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.ui_layout_function_message_managescreen_layout_roomchat);
        setSupportActionBar(mBinding.toolbar)
        mBinding.mNameProfile.text = intent.getStringExtra(PROFILE_NAME)
        Glide.with(this)
                .load(intent.getStringExtra(IMAGE_URL))
                .placeholder(R.drawable.profile_placeholder)
                .skipMemoryCache(true)
                .centerCrop()
                .into(mBinding.mImageMsgLayoutChat)
        mBinding.mStatus.text = "Online"


        mBinding.mCallButtonChat.setOnClickListener {
            mBinding.mLayoutMenuActionBar.visibility = View.GONE
            i = Intent(this, DKAMsgScreenCall::class.java)
            startActivity(i)
        }

        mBinding.mCallVideoButtonChat.setOnClickListener {
            mBinding.mLayoutMenuActionBar.visibility = View.GONE
        }
        mBinding.mSearchButton.setOnClickListener {
            mBinding.mLayoutMenuActionBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.assets_framework_dka_product_chat_chatroom, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.expandFloatingMenuChat -> {
                if (mBinding.mLayoutMenuActionBar.visibility == View.GONE) {
                    mBinding.mLayoutMenuActionBar.visibility = View.VISIBLE
                } else {
                    mBinding.mLayoutMenuActionBar.visibility = View.GONE
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}