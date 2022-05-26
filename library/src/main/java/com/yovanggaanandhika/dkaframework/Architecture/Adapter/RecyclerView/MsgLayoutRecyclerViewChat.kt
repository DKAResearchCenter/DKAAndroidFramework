package com.yovanggaanandhika.dkaframework.Architecture.Adapter.RecyclerView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenChatEntity
import com.yovanggaanandhika.dkaframework.Functions.Message.Component.View.DKAMsgScreenRoomChat
import com.yovanggaanandhika.dkaframework.R
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionMessageManagescreenRecyclerviewChatBinding

class MsgLayoutRecyclerViewChat internal constructor(var context: Context?) : RecyclerView.Adapter<MsgLayoutRecyclerViewChat.ViewHolderChat>() {

    private lateinit var i: Intent
    private lateinit var mBindingChat: UiLayoutFunctionMessageManagescreenRecyclerviewChatBinding

    private var mMsgLayoutData = emptyList<DKAMsgScreenChatEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderChat {
        mBindingChat = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.ui_layout_function_message_managescreen_recyclerview_chat, parent, false)
        return ViewHolderChat(mBindingChat)

    }


    override fun onBindViewHolder(mHolder: ViewHolderChat, position: Int) {
        Glide.with(context!!)
                .load(mMsgLayoutData[position].image)
                .placeholder(R.drawable.profile_placeholder)
                .skipMemoryCache(true)
                .centerCrop()
                .into(mHolder.bind.mImageMsgLayoutChat)

        mHolder.bind.mModelChat.setOnClickListener {
            i = Intent(context, DKAMsgScreenRoomChat::class.java)
            i.putExtra(DKAMsgScreenRoomChat.PROFILE_NAME, mMsgLayoutData[position].name)
            i.putExtra(DKAMsgScreenRoomChat.IMAGE_URL, mMsgLayoutData[position].image)
            context!!.startActivity(i)
        }
        mHolder.bind.mNameMsgLayoutChat.text = mMsgLayoutData[position].name
        mHolder.bind.mSubMsgLayoutChat.text = mMsgLayoutData[position].last_msg
        mHolder.bind.mTimeMsgLayoutChat.text = mMsgLayoutData[position].time
    }

    override fun getItemCount(): Int = mMsgLayoutData.size

    inner class ViewHolderChat(view: UiLayoutFunctionMessageManagescreenRecyclerviewChatBinding) : RecyclerView.ViewHolder(view.root) {
        val bind = view
    }


    internal fun setData(mMsgLayoutData: MutableList<DKAMsgScreenChatEntity>) {
        this.mMsgLayoutData = mMsgLayoutData
        notifyDataSetChanged()
    }
}