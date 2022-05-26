package com.dkaresearchcenter.dkaframework.Architecture.Models.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.dkaresearchcenter.dkaframework.Architecture.Models.Dao.DKAMsgScreenCallDao
import com.dkaresearchcenter.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenCallEntity

@Database(entities = [DKAMsgScreenCallEntity::class], version = 1, exportSchema = false)
internal abstract class DKAMsgScreenCallDatabase : RoomDatabase() {

    abstract fun MsgLayoutDao() : DKAMsgScreenCallDao

    companion object {
        @Volatile
        private var INSTANCE : DKAMsgScreenCallDatabase? = null

        private class MsgLayoutCallback(private val scope : CoroutineScope) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let {database ->
                    scope.launch {
                        withContext(Dispatchers.Default){
                            database.MsgLayoutDao().let {
                                it.deleteAll()
                            }
                            /*chatRoomDao.insert(ChatRoomEntity(DKA.Widget.ChatRoom.CHAT_TEXT_TYPE_ME, "Hy Apa Kabar ?"))*/
                        }

                    }
                }
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope) : DKAMsgScreenCallDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DKAMsgScreenCallDatabase::class.java,
                    "DKAMsgScreenCall"
                )
                    .addCallback(MsgLayoutCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}