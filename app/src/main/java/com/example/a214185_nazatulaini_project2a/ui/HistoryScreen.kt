package com.example.a214185_nazatulaini_project2a.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.a214185_nazatulaini_project2a.SolarViewModel
import com.example.a214185_nazatulaini_project2a.data.HistoryEntry

@Composable
fun HistoryScreen(
    viewModel: SolarViewModel,
    modifier: Modifier = Modifier
) {
    val historyList by viewModel.historyList.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = "Your Solar Setups", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(vertical = 8.dp))

        if (historyList.isEmpty()) {
            EmptyHistoryState()
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(bottom = 16.dp)) {
                items(historyList) { entry ->
                    HistoryItemCard(entry = entry)
                }
            }
        }
    }
}

@Composable
fun EmptyHistoryState() {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 64.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = Icons.Default.Info, contentDescription = "Empty", modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "No setups saved yet.", style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun HistoryItemCard(entry: HistoryEntry) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.setupName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Panels: ${entry.panels}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Wattage: ${entry.wattage} W", style = MaterialTheme.typography.bodyMedium)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Daily Output", style = MaterialTheme.typography.labelSmall)
                    Text(text = "%.2f kWh".format(entry.dailyOutput), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}