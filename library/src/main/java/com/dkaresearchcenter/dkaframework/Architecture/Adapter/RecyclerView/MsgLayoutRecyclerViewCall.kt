package com.dkaresearchcenter.dkaframework.Architecture.Adapter.RecyclerView

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenCallEntity
import com.dkaresearchcenter.dkaframework.Functions.Message.Component.View.DKAMsgScreenCall
import com.dkaresearchcenter.dkaframework.R
import com.dkaresearchcenter.dkaframework.databinding.UiLayoutFunctionMessageManagescreenRecyclerviewCallBinding

class MsgLayoutRecyclerViewCall internal constructor(var context : Context?) : RecyclerView.Adapter<MsgLayoutRecyclerViewCall.ViewHolderCall>() {

    private lateinit var mBindingCall : UiLayoutFunctionMessageManagescreenRecyclerviewCallBinding
    private lateinit var i : Intent

    private var mMsgLayoutData = emptyList<DKAMsgScreenCallEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MsgLayoutRecyclerViewCall.ViewHolderCall {
        mBindingCall = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.ui_layout_function_message_managescreen_recyclerview_call, parent, false)
        return ViewHolderCall(mBindingCall)
    }

    override fun onBindViewHolder(mHolder: MsgLayoutRecyclerViewCall.ViewHolderCall, position: Int) {
        Glide.with(context!!)
            .load(mMsgLayoutData[position].image)
            .placeholder(R.drawable.profile_placeholder)
            .skipMemoryCache(true)
            .centerCrop()
            .into(mHolder.bind.mImageMsgLayoutCall)

        mHolder.bind.mTimeMsgLayoutCall.setOnClickListener {
            i = Intent(context, DKAMsgScreenCall::class.java)
            context!!.startActivity(i)
        }

        mHolder.bind.mNameMsgLayoutCall.text = mMsgLayoutData[position].name
        mHolder.bind.mSubMsgLayoutCall.text = mMsgLayoutData[position].last_msg
    }

    override fun getItemCount(): Int = mMsgLayoutData.size


    inner class ViewHolderCall(view : UiLayoutFunctionMessageManagescreenRecyclerviewCallBinding) : RecyclerView.ViewHolder(view.root){
        val bind = view
    }


    fun setData(mMsgLayoutData: MutableList<DKAMsgScreenCallEntity>){
        this.mMsgLayoutData = mMsgLayoutData
        notifyDataSetChanged()
    }
}