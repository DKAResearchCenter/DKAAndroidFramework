package com.dkaresearchcenter.dkaframework.Architecture.Models.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenCallEntity


@Dao
interface DKAMsgScreenCallDao {
    @Query("SELECT * FROM DKAMsgScreenCall ORDER BY id ASC")
    fun readAll() : LiveData<MutableList<DKAMsgScreenCallEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(MsgLayoutData : DKAMsgScreenCallEntity)

    @Query("DELETE FROM DKAMsgScreenCall")
    suspend fun deleteAll()
}