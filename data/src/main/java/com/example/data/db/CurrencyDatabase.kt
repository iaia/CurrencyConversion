package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.LiveDao
import com.example.data.db.dao.RateDao
import com.example.data.model.Live
import com.example.data.model.Rate

@Database(
    entities = [
        Live::class,
        Rate::class
    ], version = 1
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun liveDao(): LiveDao
    abstract fun rateDao(): RateDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context): CurrencyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDatabase::class.java,
                    "currency"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}