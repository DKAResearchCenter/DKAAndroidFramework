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
import com.yovanggaanandhika.dkaframework.Architecture.Adapter.RecyclerView.MsgLayoutRecyclerViewCall
import com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenCallEntity
import com.yovanggaanandhika.dkaframework.Architecture.Models.View.RoomPersistance.DKAMsgScreenCallViewModel
import com.yovanggaanandhika.dkaframework.R
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionMessageManagescreenLayoutBinding


class MessageLayoutCall : Fragment() {

    private lateinit var mBinding : UiLayoutFunctionMessageManagescreenLayoutBinding
    private lateinit var mMsgLayoutRecylerView : MsgLayoutRecyclerViewCall
    private var mMsgLayoutViewModel: DKAMsgScreenCallViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMsgLayoutViewModel = ViewModelProviders.of(this).get(DKAMsgScreenCallViewModel::class.java)


    }
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMsgLayoutRecylerView =
            MsgLayoutRecyclerViewCall(
                context
            )
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.ui_layout_function_message_managescreen_layout,
            container,
            false
        )

        mBinding.mRecyclerChat.apply {
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = DefaultItemAnimator()
            adapter = mMsgLayoutRecylerView

        }

        mMsgLayoutViewModel!!.insert(
            DKAMsgScreenCallEntity(
                1,
                1,
                "Yovangga Anandhika",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7UKHNG8aPc_ZwGveWNTD1QXXu7kxiRu9knw&usqp=CAU",
                "7.00",
                "4 Menit Lalu"
            )
        )
        mMsgLayoutViewModel!!.insert(
            DKAMsgScreenCallEntity(
                2,
                1,
                "Muhammad Fachri",
                "",
                "6.70",
                "4 Menit Lalu"
            )
        )
        mMsgLayoutViewModel!!.insert(
            DKAMsgScreenCallEntity(
                3,
                1,
                "Murijal Irawan",
                "https://pbs.twimg.com/profile_images/1251179130323791873/QqSlPi5A.jpg",
                "14.30",
                "4 Menit Lalu"
            )
        )
        mMsgLayoutViewModel!!.insert(
            DKAMsgScreenCallEntity(
                44,
                1,
                "DKA Group",
                "",
                "14.30",
                "4 Menit Lalu"
            )
        )

        mMsgLayoutViewModel?.readAll?.observe(this, {
            if (it.size > 0) {
                it.let {
                    mMsgLayoutRecylerView.setData(it)
                }
            } else {

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