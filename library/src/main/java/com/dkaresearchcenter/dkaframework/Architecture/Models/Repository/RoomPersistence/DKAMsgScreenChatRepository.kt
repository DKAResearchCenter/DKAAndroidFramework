package com.dkaresearchcenter.dkaframework.Architecture.Models.Repository.RoomPersistence

import androidx.lifecycle.LiveData
import com.dkaresearchcenter.dkaframework.Architecture.Models.Dao.DKAMsgScreenChatDao
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenChatEntity


class DKAMsgScreenChatRepository(private var MsgLayoutDao : DKAMsgScreenChatDao){

    val readAll : LiveData<MutableList<DKAMsgScreenChatEntity>> = MsgLayoutDao.readAll()


    suspend fun insert(MsgLayout: DKAMsgScreenChatEntity){
        MsgLayoutDao.insert(MsgLayout)
    }

    suspend fun deleteAll(){
        MsgLayoutDao.deleteAll()
    }
}