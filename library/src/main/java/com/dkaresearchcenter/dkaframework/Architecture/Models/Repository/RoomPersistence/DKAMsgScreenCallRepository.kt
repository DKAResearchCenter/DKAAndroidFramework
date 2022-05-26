package com.dkaresearchcenter.dkaframework.Architecture.Models.Repository.RoomPersistence

import androidx.lifecycle.LiveData
import com.dkaresearchcenter.dkaframework.Architecture.Models.Dao.DKAMsgScreenCallDao
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenCallEntity


class DKAMsgScreenCallRepository(private var MsgLayoutDao: DKAMsgScreenCallDao) {

    val readAll: LiveData<MutableList<DKAMsgScreenCallEntity>> = MsgLayoutDao.readAll()


    suspend fun insert(MsgLayout: DKAMsgScreenCallEntity) {
        MsgLayoutDao.insert(MsgLayout)
    }

    suspend fun deleteAll() {
        MsgLayoutDao.deleteAll()
    }
}