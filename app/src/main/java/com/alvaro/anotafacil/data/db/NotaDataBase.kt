package com.alvaro.anotafacil.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alvaro.anotafacil.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NotaDataBase: RoomDatabase() {

    abstract fun notaDao(): NotaDao

    companion object {
        @Volatile
        private var instance: NotaDataBase? = null
        fun getDataBase(context: Context): NotaDataBase {
            val tempIntance = instance
            if (tempIntance != null) {
                return tempIntance
            }
            synchronized(this) {
                val _instance = Room.databaseBuilder(context.applicationContext,
                    NotaDataBase::class.java, "123").build()
                instance = _instance
                return _instance
            }
        }
    }

}