package com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class DKAMsgDataRoomChat(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id : Int? = null,

) {
}