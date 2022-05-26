package com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.System

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "DKA_USER_LOGIN")
data class DKAAccountLoginEntity (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ID) var id : Int? = null,
        @ColumnInfo(name = FULL_NAME) var full_name : String? = null,
        @ColumnInfo(name = PHONE_NUMBER) val phone_number : Int? = null,
        @ColumnInfo(name = GENDRE) val gendre : String? = null,
        @ColumnInfo(name = PHOTOS) val photos : String? = null,
        @ColumnInfo(name = USERNAME) var username : String? = null,
        @ColumnInfo(name = EMAIL) var email : String? = null,
        @ColumnInfo(name = PASSWORD) var password : String? = null,
        @ColumnInfo(name = REGISTER_DATETIME) var register_date : String? = null,
        @ColumnInfo(name = DELETE_STATUS) var delete_status : Boolean? = null,
        @ColumnInfo(name = BANNED_STATUS) var banned_status : Boolean? = null

){
    constructor() : this(null, null, null, null, null, null, null, null, null, null, null)

    companion object {
        const val ID = "ID"
        const val FULL_NAME = "FULL_NAME"
        const val PHONE_NUMBER = "PHONE_NUMBER"
        const val GENDRE = "GENDRE"
        const val PHOTOS = "PHOTOS"
        const val USERNAME = "USERNAME"
        const val EMAIL = "EMAIL"
        const val PASSWORD = "PASSWORD"
        const val REGISTER_DATETIME = "REGISTER_DATE"
        const val DELETE_STATUS = "DELETE_STATUS"
        const val BANNED_STATUS = "BANNED_STATUS"
    }
}