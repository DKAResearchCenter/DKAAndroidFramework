package com.dkaresearchcenter.dkaframework.Architecture.Models.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenChatEntity


@Dao
interface DKAMsgScreenChatDao {
    @Query("SELECT * FROM DKAMsgScreenChat ORDER BY id ASC")
    fun readAll() : LiveData<MutableList<DKAMsgScreenChatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(MsgLayoutData : DKAMsgScreenChatEntity)

    @Query("DELETE FROM DKAMsgScreenChat")
    suspend fun deleteAll()
}