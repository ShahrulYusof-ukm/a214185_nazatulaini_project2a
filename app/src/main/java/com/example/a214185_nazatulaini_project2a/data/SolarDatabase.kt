package com.example.a214185_nazatulaini_project2a.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntry::class], version = 1, exportSchema = false)
abstract class SolarDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var Instance: SolarDatabase? = null

        fun getDatabase(context: Context): SolarDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SolarDatabase::class.java,
                    "solar_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}