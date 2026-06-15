package com.example.a214185_nazatulaini_project2a.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entry: HistoryEntry): Long

    @Query("SELECT * FROM history_entries ORDER BY id DESC")
    fun getAllEntries(): Flow<List<HistoryEntry>> // Returns an asynchronous data stream
}