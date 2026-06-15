package com.example.a214185_nazatulaini_project2a.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a214185_nazatulaini_project2a.SolarViewModel

@Composable
fun ResultScreen(
    viewModel: SolarViewModel,
    onBackToHome: () -> Unit,
    onViewHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentData by viewModel.currentData.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "✅", fontSize = 64.sp, modifier = Modifier.padding(top = 16.dp))

        Text(
            text = "Setup Saved & Calculated!",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "ESTIMATED ENERGY OUTPUT", style = MaterialTheme.typography.labelMedium)
                HorizontalDivider()

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Daily Output", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "%.2f kWh".format(currentData.dailyOutput),
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Monthly Output", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "%.2f kWh".format(currentData.monthlyOutput),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "SETUP DETAILS", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(12.dp))
                ResultDetailRow(label = "Number of Panels:", value = "${currentData.panels}")
                Spacer(modifier = Modifier.height(8.dp))
                ResultDetailRow(label = "Panel Wattage:", value = "${currentData.wattage} W")
                Spacer(modifier = Modifier.height(8.dp))
                ResultDetailRow(label = "Peak Sun Hours:", value = "${currentData.sunHours} hrs/day")
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = onBackToHome, modifier = Modifier.weight(1f).height(56.dp)) {
                Icon(Icons.Default.Home, contentDescription = "Home")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Home")
            }

            Button(onClick = onViewHistory, modifier = Modifier.weight(1f).height(56.dp)) {
                Icon(Icons.Default.List, contentDescription = "History")
                Spacer(modifier = Modifier.width(4.dp))
                Text("History")
            }
        }
    }
}

@Composable
fun ResultDetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}