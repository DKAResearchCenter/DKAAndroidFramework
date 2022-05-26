package com.yovanggaanandhika.dkaframework.Architecture.Models.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.yovanggaanandhika.dkaframework.Architecture.Models.Dao.DKAMsgScreenChatDao
import com.yovanggaanandhika.dkaframework.Architecture.Models.Entity.Feature.Message.View.DKAMsgScreenChatEntity

@Database(entities = [DKAMsgScreenChatEntity::class], version = 1, exportSchema = false)
internal abstract class DKAMsgScreenChatDatabase : RoomDatabase() {

    abstract fun MsgLayoutDao() : DKAMsgScreenChatDao

    companion object {
        @Volatile
        private var INSTANCE : DKAMsgScreenChatDatabase? = null

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

        fun getDatabase(context: Context, scope: CoroutineScope) : DKAMsgScreenChatDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DKAMsgScreenChatDatabase::class.java,
                    "DKAMsgScreenChat"
                )
                    .addCallback(MsgLayoutCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}