package com.dkaresearchcenter.dkaframework.Architecture.Models.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenGroupEntity

@Dao
interface DKAMsgScreenGroupDao {
    @Query("SELECT * FROM DKAMsgScreenGroup ORDER BY id ASC")
    fun readAll() : LiveData<MutableList<DKAMsgScreenGroupEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(MsgLayoutData : DKAMsgScreenGroupEntity)

    @Query("DELETE FROM DKAMsgScreenGroup")
    suspend fun deleteAll()
}