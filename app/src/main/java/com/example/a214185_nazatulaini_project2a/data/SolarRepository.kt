package com.example.a214185_nazatulaini_project2a.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SolarRepository(private val historyDao: HistoryDao) {

    fun getAllEntriesStream(): Flow<List<HistoryEntry>> = historyDao.getAllEntries()

    suspend fun insertEntry(entry: HistoryEntry) {
        withContext(Dispatchers.IO) {
            historyDao.insert(entry)
        }
    }
}