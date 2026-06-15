package com.example.a214185_nazatulaini_project2a.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// 1. Mark this data class as a Room Database Entity
@Entity(tableName = "history_entries")
data class HistoryEntry(
    // 2. Set up an auto-incrementing Integer Primary Key for SQLite
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val setupName: String,
    val panels: Int,
    val wattage: Double,
    val dailyOutput: Double
)