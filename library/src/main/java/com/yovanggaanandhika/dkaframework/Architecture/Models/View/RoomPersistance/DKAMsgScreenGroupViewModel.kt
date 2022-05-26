package com.yovanggaanandhika.dkaframework.Architecture.Models.View.RoomPersistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.yovanggaanandhika.dkaframework.Architecture.Models.Database.DKAMsgScreenGroupDatabase
import com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenGroupEntity
import com.yovanggaanandhika.dkaframework.Architecture.Models.Repository.RoomPersistence.DKAMsgScreenGroupRepository

import com.yovanggaanandhika.dkaframework.DKA

class DKAMsgScreenGroupViewModel : ViewModel() {

    private var BACKGROUND_THREAD = Dispatchers.Default

    private val repository: DKAMsgScreenGroupRepository
    val readAll: LiveData<MutableList<DKAMsgScreenGroupEntity>>

    init {
        val MsgLayoutDao = DKAMsgScreenGroupDatabase.getDatabase(DKA.CONTEXT, viewModelScope).MsgLayoutDao()
        repository = DKAMsgScreenGroupRepository(MsgLayoutDao)
        readAll = repository.readAll
    }

    fun insert(MsgLayout: DKAMsgScreenGroupEntity) = viewModelScope.launch {
        withContext(BACKGROUND_THREAD) {
            repository.insert(MsgLayout)
        }
    }
}