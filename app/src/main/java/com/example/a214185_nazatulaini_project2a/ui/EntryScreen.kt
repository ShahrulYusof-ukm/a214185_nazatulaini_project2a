package com.example.a214185_nazatulaini_project2a.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a214185_nazatulaini_project2a.SolarViewModel

@Composable
fun AddEntryScreen(
    viewModel: SolarViewModel,
    onNavigateToResult: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var setupName by remember { mutableStateOf("") }
    var panelCount by remember { mutableStateOf("") }
    var wattage by remember { mutableStateOf("") }
    var sunHours by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Enter Solar Setup Details",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = setupName,
                    onValueChange = { setupName = it; showError = false },
                    label = { Text("Setup Name (e.g., Home Roof)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = panelCount,
                    onValueChange = { panelCount = it; showError = false },
                    label = { Text("Number of Panels") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = wattage,
                    onValueChange = { wattage = it; showError = false },
                    label = { Text("Panel Wattage (W)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = sunHours,
                    onValueChange = { sunHours = it; showError = false },
                    label = { Text("Peak Sun Hours / Day") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    modifier = Modifier.fillMaxWidth()
                )

                if (showError) {
                    Text(
                        text = "Please fill in all fields correctly with numbers.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f).height(56.dp)
            ) {
                Icon(Icons.Default.Clear, contentDescription = "Cancel")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cancel")
            }

            Button(
                onClick = {
                    val panels = panelCount.toIntOrNull()
                    val watt = wattage.toDoubleOrNull()
                    val sun = sunHours.toDoubleOrNull()

                    if (setupName.isNotBlank() && panels != null && watt != null && sun != null) {
                        viewModel.calculateCurrent(panels, watt, sun)
                        viewModel.addSetupToHistory(setupName, panels, watt, sun)
                        onNavigateToResult()
                    } else {
                        showError = true
                    }
                },
                modifier = Modifier.weight(1f).height(56.dp)
            ) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Save")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Save", fontSize = 16.sp)
            }
        }
    }
}