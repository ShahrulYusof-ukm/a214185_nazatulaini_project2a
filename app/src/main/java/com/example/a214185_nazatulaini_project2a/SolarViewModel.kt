package com.example.a214185_nazatulaini_project2a

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewModelScope
import com.example.a214185_nazatulaini_project2a.data.HistoryEntry
import com.example.a214185_nazatulaini_project2a.data.SolarData
import com.example.a214185_nazatulaini_project2a.data.SolarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SolarViewModel(private val repository: SolarRepository) : ViewModel() {

    // 1. In-memory temporary state for the Result Screen calculation
    private val _currentData = MutableStateFlow(SolarData())
    val currentData: StateFlow<SolarData> = _currentData.asStateFlow()

    // 2. Persistent flow streaming values straight out of Room database
    val historyList: StateFlow<List<HistoryEntry>> = repository.getAllEntriesStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun calculateCurrent(panels: Int, wattage: Double, sunHours: Double) {
        val daily = (panels * wattage * sunHours) / 1000.0
        val monthly = daily * 30.0

        _currentData.update {
            it.copy(
                panels = panels,
                wattage = wattage,
                sunHours = sunHours,
                dailyOutput = daily,
                monthlyOutput = monthly
            )
        }
    }

    // Updated to save objects directly inside Room database using an asynchronous coroutine scope
    fun addSetupToHistory(setupName: String, panels: Int, wattage: Double, sunHours: Double) {
        val daily = (panels * wattage * sunHours) / 1000.0

        val newEntry = HistoryEntry(
            setupName = setupName,
            panels = panels,
            wattage = wattage,
            dailyOutput = daily
        )

        viewModelScope.launch {
            repository.insertEntry(newEntry)
        }
    }

    fun resetCurrentData() {
        _currentData.value = SolarData()
    }

    // Factory companion object to safely instantiate the ViewModel with its repository dependency
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SolarApplication
                return SolarViewModel(application.repository) as T
            }
        }
    }
}