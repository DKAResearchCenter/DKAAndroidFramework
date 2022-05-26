package com.dkaresearchcenter.dkaframework.Architecture.Adapter.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenGroupEntity
import com.dkaresearchcenter.dkaframework.R
import com.dkaresearchcenter.dkaframework.databinding.UiLayoutFunctionMessageManagescreenRecyclerviewGroupBinding

class MsgLayoutRecyclerViewGroup internal constructor(var context : Context?) : RecyclerView.Adapter<MsgLayoutRecyclerViewGroup.ViewHolderGroup>() {

    private lateinit var mBindingGroup : UiLayoutFunctionMessageManagescreenRecyclerviewGroupBinding

    private var mMsgLayoutData = emptyList<DKAMsgScreenGroupEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolderGroup {
        mBindingGroup = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.ui_layout_function_message_managescreen_recyclerview_group, parent, false)
        return ViewHolderGroup(mBindingGroup)
    }


    override fun onBindViewHolder(mHolder: ViewHolderGroup, position: Int) {
        Glide.with(context!!)
            .load(mMsgLayoutData[position].image)
            .placeholder(R.drawable.profile_placeholder)
            .skipMemoryCache(true)
            .centerCrop()
            .into(mHolder.bind.mImageMsgLayoutGroup)
        mHolder.bind.mNameMsgLayoutGroup.text = mMsgLayoutData[position].name
        mHolder.bind.mSubMsgLayoutGroup.text = mMsgLayoutData[position].last_msg
        mHolder.bind.mTimeMsgLayoutGroup.text = mMsgLayoutData[position].time
    }

    override fun getItemCount(): Int = mMsgLayoutData.size

    inner class ViewHolderGroup(view : UiLayoutFunctionMessageManagescreenRecyclerviewGroupBinding) : RecyclerView.ViewHolder(view.root){
        val bind = view
    }

    fun setData(mMsgLayoutData: MutableList<DKAMsgScreenGroupEntity>){
        this.mMsgLayoutData = mMsgLayoutData
        notifyDataSetChanged()
    }
}