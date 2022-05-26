package com.yovanggaanandhika.dkaframework.Architecture.Models.View.RoomPersistance

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.yovanggaanandhika.dkaframework.Architecture.Models.Database.DKAMsgScreenChatDatabase
import com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenChatEntity
import com.yovanggaanandhika.dkaframework.Architecture.Models.Repository.RoomPersistence.DKAMsgScreenChatRepository
import com.yovanggaanandhika.dkaframework.DKA

class DKAMsgScreenChatViewModel : ViewModel() {

    private var BACKGROUND_THREAD = Dispatchers.Default

    private val repository: DKAMsgScreenChatRepository
    val readAll: LiveData<MutableList<DKAMsgScreenChatEntity>>

    init {
        val MsgLayoutDao = DKAMsgScreenChatDatabase.getDatabase(DKA.CONTEXT, viewModelScope).MsgLayoutDao()
        repository = DKAMsgScreenChatRepository(MsgLayoutDao)
        readAll = repository.readAll
    }

    fun insert(MsgLayout: DKAMsgScreenChatEntity) = viewModelScope.launch {
        withContext(BACKGROUND_THREAD) {
            repository.insert(MsgLayout)
        }
    }
}