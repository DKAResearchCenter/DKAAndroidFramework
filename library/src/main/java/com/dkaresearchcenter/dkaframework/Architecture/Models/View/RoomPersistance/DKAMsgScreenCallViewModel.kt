package com.dkaresearchcenter.dkaframework.Architecture.Models.View.RoomPersistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.dkaresearchcenter.dkaframework.Architecture.Models.Database.DKAMsgScreenCallDatabase
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenCallEntity
import com.dkaresearchcenter.dkaframework.Architecture.Models.Repository.RoomPersistence.DKAMsgScreenCallRepository

import com.dkaresearchcenter.dkaframework.DKA

class DKAMsgScreenCallViewModel : ViewModel() {

    private var BACKGROUND_THREAD = Dispatchers.Default

    private val repository: DKAMsgScreenCallRepository
    val readAll: LiveData<MutableList<DKAMsgScreenCallEntity>>

    init {
        val MsgLayoutDao = DKAMsgScreenCallDatabase.getDatabase(DKA.CONTEXT, viewModelScope).MsgLayoutDao()
        repository = DKAMsgScreenCallRepository(MsgLayoutDao)
        readAll = repository.readAll
    }

    fun insert(MsgLayout: DKAMsgScreenCallEntity) = viewModelScope.launch {
        withContext(BACKGROUND_THREAD) {
            repository.insert(MsgLayout)
        }
    }
}