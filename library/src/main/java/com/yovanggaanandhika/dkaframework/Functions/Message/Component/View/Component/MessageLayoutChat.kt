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
import com.yovanggaanandhika.dkaframework.Architecture.Adapter.RecyclerView.MsgLayoutRecyclerViewChat
import com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenChatEntity
import com.yovanggaanandhika.dkaframework.Architecture.Models.View.RoomPersistance.DKAMsgScreenChatViewModel
import com.yovanggaanandhika.dkaframework.R
import com.yovanggaanandhika.dkaframework.databinding.UiLayoutFunctionMessageManagescreenLayoutBinding

class MessageLayoutChat : Fragment() {

    private lateinit var mBinding : UiLayoutFunctionMessageManagescreenLayoutBinding
    private lateinit var mMsgLayoutRecylerView : MsgLayoutRecyclerViewChat
    private var mMsgLayoutViewModel: DKAMsgScreenChatViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMsgLayoutViewModel = ViewModelProviders.of(this).get(DKAMsgScreenChatViewModel::class.java)


    }
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMsgLayoutRecylerView = MsgLayoutRecyclerViewChat(context)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.ui_layout_function_message_managescreen_layout, container, false)

        mBinding.mRecyclerChat.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = mMsgLayoutRecylerView


        }


        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(1, 1, "Yovangga Anandhika", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7UKHNG8aPc_ZwGveWNTD1QXXu7kxiRu9knw&usqp=CAU", "Hai Apa Kabar","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(2, 1, "Murijal Irawan", "https://pbs.twimg.com/profile_images/1251179130323791873/QqSlPi5A.jpg", "Apa Kabar Kamu ?","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(3, 1, "Muhammad Arfan", "https://cdn-image.hipwee.com/wp-content/uploads/2017/01/hipwee-hipwee-id.bookmyshow.com_-750x422.jpg", "Apa Yang Terjadi Hari Ini ?","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(4, 1, "Muh Ridwan", "https://4.bp.blogspot.com/-envFi-x4GSA/U_Iy8d4zJuI/AAAAAAAAA4g/B-8yoEQmmss/s1600/DSC_0843.JPG", "Entah Apa Yang Merasukimu","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(5, 1, "Yusril Syaputra", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQc96pLM4LDn3fk-l-w2aWSgH38_LHmqisEEg&usqp=CAU", "Gimana Kabar Doi ?","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(6, 1, "Muhammad Yusuf", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmOvwTPJQDgj2hihfJnEVKvTKjheOb9gFjaQ&usqp=CAU", "Aku Katakan Cinta CInta Cinta","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(7, 1, "Nur Inzani", "https://4.bp.blogspot.com/-F_Ut7AEIQfQ/XHaGuQrxtFI/AAAAAAAADHg/rK1wVv2rT9oUDvp2MQHuCaXmLlVQtyDOQCLcBGAs/s1600/gaya-foto-selfie-kekinian-terbaru-4.jpg", "Bagaimana Bisa ?","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(8, 1, "Ramadhanti", "https://3.bp.blogspot.com/-Y7mtERgYohs/XHaGuzlpViI/AAAAAAAADHk/rAw3HYqDTrYneSN0tDcled4QkYMDxjJYgCLcBGAs/w800/gaya-foto-selfie-kekinian-terbaru-5.jpg", "Apa Yang Harus Aku Lakukan ?","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(9, 1, "Wahyu", "https://pbs.twimg.com/profile_images/1251179130323791873/QqSlPi5A.jpg", "Kamu Sakit ?","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(10, 1, "RI 1", "https://i.pinimg.com/736x/26/47/06/264706747ea4b6b9407adde231bcfe45.jpg", "Jadi Aku Harus Apa ?","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(11, 1, "Ponya", "https://i.pinimg.com/736x/fc/27/aa/fc27aa43212721a01f6cc49ac44ee726.jpg", "14.30","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(12, 1, "Maemunah", "https://pbs.twimg.com/profile_images/1251179130323791873/QqSlPi5A.jpg", "14.30","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(13, 1, "Ratna", "https://i.pinimg.com/originals/e4/3f/dc/e43fdc056574e42ca957afe876ec958c.jpg", "14.30","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(14, 1, "Muh Handoko", "https://2.bp.blogspot.com/-Ed0lRlPAcio/Vp5Zw70-3ZI/AAAAAAAABHw/Z2naEZhf3gQ/s1600/13-tanggapan-cewek-kalau-melihat-cowok-suka-selfie-9.JPG", "14.30","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(15, 1, "Putri Permata Emas", "data:image/jpeg;base64,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", "14.30","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(16, 1, "Penipu 1", "https://cdns.klimg.com/kapanlagi.com/g/s/e/selfie_ganteng_chumnam_cowok_thailand_suka_makan_sambil_jongkok/p/chumnam_maunghong-20171221-010-rita.jpg", "14.30","4 Menit Lalu"))
        mMsgLayoutViewModel!!.insert(DKAMsgScreenChatEntity(17, 1, "Dosen Unhas", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWwprkfExzCUeC1vIgJAyrk9oxTxU6vg9GqQ&usqp=CAU", "14.30","4 Menit Lalu"))

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