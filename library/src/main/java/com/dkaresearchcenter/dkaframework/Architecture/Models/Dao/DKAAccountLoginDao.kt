package com.dkaresearchcenter.dkaframework.Architecture.Models.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.System.DKAAccountLoginEntity

@Dao
interface DKAAccountLoginDao {

    @Query("SELECT * FROM `DKA_USER_LOGIN` ORDER BY ID ASC")
    fun ReadAll() : LiveData<MutableList<DKAAccountLoginEntity>>

    @Query("SELECT * FROM `DKA_USER_LOGIN` WHERE ID =:ID ")
    suspend fun Read(ID : Int?) : LiveData<DKAAccountLoginEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun Insert(DKAAccountLoginDao : DKAAccountLoginEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun UpdateAll(DKAAccountLoginDao : DKAAccountLoginEntity)

    @Query("UPDATE `DKA_USER_LOGIN` SET DELETE_STATUS=:DELETE_STATUS WHERE ID = :ID ")
    suspend fun delete(ID: Int?, DELETE_STATUS : Boolean?)

    @Query("UPDATE `DKA_USER_LOGIN` SET DELETE_STATUS=:DELETE_STATUS")
    suspend fun deleteAll(DELETE_STATUS : Boolean?)
}