package com.example.a214185_nazatulaini_project2a.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SdgInfoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(100.dp).clip(CircleShape).background(MaterialTheme.colorScheme.tertiaryContainer), contentAlignment = Alignment.Center) {
            Text(text = "⚡", fontSize = 50.sp)
        }

        Text(text = "Goal 7: Affordable and Clean Energy", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center)

        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(text = "KEY TARGETS BY 2030", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                Row {
                    Icon(Icons.Default.CheckCircle, contentDescription = "Check", tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Increase substantially the share of renewable energy in the global energy mix.")
                }
            }
        }
    }
}