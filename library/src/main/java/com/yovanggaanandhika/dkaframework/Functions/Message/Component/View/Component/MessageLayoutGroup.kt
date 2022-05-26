@file:Suppress("DEPRECATION")

package com.yovanggaanandhika.dkaframework.Functions.Message.Component.View.Component

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.yovanggaanandhika.dkaframework.Architecture.Adapter.RecyclerView.MsgLayoutRecyclerViewGroup
import com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenGroupEntity
import com.yovanggaanandhika.dkaframework.Architecture.Models.View.RoomPersistance.DKAMsgScreenGroupViewModel
import com.yovanggaanandhika.dkaframework.R
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionMessageManagescreenLayoutBinding

class MessageLayoutGroup : Fragment() {

    private lateinit var mBinding : UiLayoutFunctionMessageManagescreenLayoutBinding
    private lateinit var mMsgLayoutRecylerView : MsgLayoutRecyclerViewGroup
    private var mMsgLayoutViewModel: DKAMsgScreenGroupViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMsgLayoutViewModel = ViewModelProviders.of(this).get(DKAMsgScreenGroupViewModel::class.java)


    }
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMsgLayoutRecylerView = MsgLayoutRecyclerViewGroup(context)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.ui_layout_function_message_managescreen_layout, container, false)

        mBinding.mRecyclerChat.apply {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = mMsgLayoutRecylerView

        }

        mMsgLayoutViewModel!!.insert(DKAMsgScreenGroupEntity(44, 1, "DKA Group", "", "Harus Mempertimbangkan Aspek Lain","4 Menit Lalu"))

        mMsgLayoutViewModel?.readAll?.observe(this, {
            if (it.size > 0) {
                it.let {
                    mMsgLayoutRecylerView.setData(it)
                }
            }else{

            }
        })




        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()

    }


}