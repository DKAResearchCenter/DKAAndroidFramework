package com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DKAMsgScreenCall")
data class DKAMsgScreenCallEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id : Int? = null,
    @ColumnInfo(name = "id_user") var id_user : Int? = null,
    @ColumnInfo(name = "name") var name : String? = null,
    @ColumnInfo(name = "image") var image : String? = null,
    @ColumnInfo(name = "last_msg") var last_msg : String? = null,
    @ColumnInfo(name = "time") var time : String? = null
){
    constructor() : this(null,null,null,null,null, null)

    constructor(id_user: Int?, name : String?, image: String?, last_msg: String?, time: String?) : this(null, id_user, name, image, last_msg, time)
}