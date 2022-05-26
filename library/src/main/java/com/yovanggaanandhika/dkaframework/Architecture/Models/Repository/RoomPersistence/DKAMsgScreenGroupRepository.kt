package com.yovanggaanandhika.dkaframework.Architecture.Models.Repository.RoomPersistence

import androidx.lifecycle.LiveData
import com.yovanggaanandhika.dkaframework.Architecture.Models.Dao.DKAMsgScreenGroupDao
import com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenGroupEntity


class DKAMsgScreenGroupRepository(private var MsgLayoutDao: DKAMsgScreenGroupDao) {

    val readAll: LiveData<MutableList<DKAMsgScreenGroupEntity>> = MsgLayoutDao.readAll()


    suspend fun insert(MsgLayout: DKAMsgScreenGroupEntity) {
        MsgLayoutDao.insert(MsgLayout)
    }

    suspend fun deleteAll() {
        MsgLayoutDao.deleteAll()
    }
}