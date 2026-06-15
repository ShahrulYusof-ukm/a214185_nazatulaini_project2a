package com.example.a214185_nazatulaini_project2a.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onAddSetupClick: () -> Unit,
    onViewHistoryClick: () -> Unit,
    onSdgInfoClick: () -> Unit,
    onForecastClick: () -> Unit, // New parameter for Screen 6
    onCloudClick: () -> Unit,    // New parameter for Screen 7
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "🌞", fontSize = 48.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Solar Setup Tracker",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Track and estimate your clean energy output",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "THE PROBLEM & IMPACT",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Transitioning to clean energy is critical, but many communities lack simple tools to estimate the benefits of solar panels. By tracking solar setups, we can visualize energy savings, encourage renewable adoption, and reduce reliance on fossil fuels.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Screen 2 Trigger
        Button(
            onClick = onAddSetupClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Setup")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Add New Setup", fontSize = 16.sp)
        }

        // Screen 4 Trigger
        FilledTonalButton(
            onClick = onViewHistoryClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(Icons.Default.List, contentDescription = "History")
            Spacer(modifier = Modifier.width(8.dp))
            Text("View Setup History", fontSize = 16.sp)
        }

        // 🆕 Screen 6 Trigger: Solar Forecast (API + Location)
        FilledTonalButton(
            onClick = onForecastClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        ) {
            Icon(Icons.Default.Refresh, contentDescription = "Forecast")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Live Solar Forecast (API)", fontSize = 16.sp)
        }

        // 🆕 Screen 7 Trigger: Cloud Dashboard (Firebase)
        FilledTonalButton(
            onClick = onCloudClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        ) {
            Icon(Icons.Default.Share, contentDescription = "Cloud")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Cloud Community Feed (Firebase)", fontSize = 16.sp)
        }

        // Screen 5 Trigger
        OutlinedButton(
            onClick = onSdgInfoClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(Icons.Default.Info, contentDescription = "Info")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Learn About SDG 7", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}