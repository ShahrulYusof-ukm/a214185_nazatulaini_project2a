package com.example.a214185_nazatulaini_project2a

import android.app.Application
import com.example.a214185_nazatulaini_project2a.data.SolarDatabase
import com.example.a214185_nazatulaini_project2a.data.SolarRepository

class SolarApplication : Application() {
    val database: SolarDatabase by lazy { SolarDatabase.getDatabase(this) }
    val repository: SolarRepository by lazy { SolarRepository(database.historyDao()) }
}