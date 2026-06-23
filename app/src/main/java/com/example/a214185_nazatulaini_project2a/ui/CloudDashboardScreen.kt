package com.example.a214185_nazatulaini_project2a.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

data class CloudSetupEntry(
    val id: String = "",
    val locationName: String = "",
    val panelsInstalled: Int = 0,
    val generationKwh: Double = 0.0
)

@Composable
fun CloudDashboardScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // 🛡️ CRASH GUARD: Wrapped initialization inside a try-catch block
    val firestore: FirebaseFirestore? = remember {
        try {
            FirebaseFirestore.getInstance()
        } catch (e: Exception) {
            android.util.Log.e("FirebaseInit", "Firestore initialization failed", e)
            null
        }
    }

    var cloudItems by remember { mutableStateOf<List<CloudSetupEntry>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var isUploading by remember { mutableStateOf(false) }

    fun fetchCloudData() {
        if (firestore == null) {
            // Safe fallback simulation data block
            cloudItems = listOf(
                CloudSetupEntry("1", "Bangi Green Residence (Simulated)", 12, 54.8),
                CloudSetupEntry("2", "Putrajaya Solar Plant Hub (Simulated)", 120, 642.0),
                CloudSetupEntry("3", "UKM Engineering Lab Roof (Simulated)", 24, 110.5)
            )
            Toast.makeText(context, "Using simulated data (Firebase not initialized)", Toast.LENGTH_SHORT).show()
            return
        }

        isLoading = true
        firestore.collection("community_setups")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val fetchedList = result.map { document ->
                    CloudSetupEntry(
                        id = document.id,
                        locationName = document.getString("locationName") ?: "Unknown Location",
                        panelsInstalled = document.getLong("panelsInstalled")?.toInt() ?: 0,
                        generationKwh = document.getDouble("generationKwh") ?: 0.0
                    )
                }
                cloudItems = fetchedList
                isLoading = false
            }
            .addOnFailureListener { e ->
                isLoading = false
                android.util.Log.e("FirestoreError", "Fetch failed", e)
                Toast.makeText(context, "Fetch failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
    }

    fun uploadSetupToCloud() {
        val itemPayload = hashMapOf(
            "locationName" to "User Share ${System.currentTimeMillis() % 10000}",
            "panelsInstalled" to 8,
            "generationKwh" to 36.4,
            "timestamp" to System.currentTimeMillis()
        )

        if (firestore == null) {
            // Append locally to show the feature works conceptually on screen
            cloudItems = cloudItems + CloudSetupEntry(
                id = System.currentTimeMillis().toString(),
                locationName = itemPayload["locationName"] as String,
                panelsInstalled = itemPayload["panelsInstalled"] as Int,
                generationKwh = itemPayload["generationKwh"] as Double
            )
            Toast.makeText(context, "App Guard: Running local cloud feed simulation.", Toast.LENGTH_LONG).show()
            return
        }

        isUploading = true
        firestore.collection("community_setups")
            .add(itemPayload)
            .addOnSuccessListener {
                isUploading = false
                Toast.makeText(context, "Successfully broadcasted to Cloud Feed!", Toast.LENGTH_SHORT).show()
                fetchCloudData()
            }
            .addOnFailureListener { e ->
                isUploading = false
                android.util.Log.e("FirestoreError", "Upload failed", e)
                Toast.makeText(context, "Upload failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
    }

    LaunchedEffect(Unit) {
        fetchCloudData()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "☁️ Malaysia Clean Energy Feed",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = if (firestore == null)
                        "⚠️ WARNING: Google Services configuration missing. Running standalone network simulation feed mode."
                    else
                        "Connected to Firebase Firestore. Read live contributions from other users or tap share to backup yours.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { uploadSetupToCloud() },
                enabled = !isUploading,
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Share, contentDescription = "Broadcast")
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (isUploading) "Broadcasting..." else "Share My Setup")
            }

            OutlinedButton(
                onClick = { fetchCloudData() },
                enabled = !isLoading,
                modifier = Modifier.width(120.dp)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }

        Text(
            text = "LIVE CONTRIBUTIONS",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.outline
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(cloudItems) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = item.locationName, fontWeight = FontWeight.Bold)
                            Text(
                                text = "⚡ Panels: ${item.panelsInstalled} units",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Text(
                            text = "${item.generationKwh} kWh/day",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}