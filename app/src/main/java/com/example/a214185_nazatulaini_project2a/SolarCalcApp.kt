package com.example.a214185_nazatulaini_project2a

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.a214185_nazatulaini_project2a.ui.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.a214185_nazatulaini_project2a.ui.theme.A214185_nazatulaini_project2aTheme

enum class SolarScreen(val title: String) {
    Home("☀ Solar Setup Tracker"),
    AddEntry("Add New Setup"),
    Result("Calculation Result"),
    History("Setup History"),
    SdgInfo("About SDG 7"),
    SolarForecast("Live Solar Forecast"),      // Screen 6: Location + Retrofit API
    CloudDashboard("Cloud Community Feed")    // Screen 7: Firebase Firestore Cloud
}

@Composable
fun SolarProjectApp(
    viewModel: SolarViewModel = viewModel(factory = SolarViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: SolarScreen.Home.name

    val currentScreen = try {
        SolarScreen.valueOf(currentRoute)
    } catch (_: IllegalArgumentException) {
        SolarScreen.Home
    }

    SolarProjectAppContent(
        currentScreen = currentScreen,
        canNavigateBack = navController.previousBackStackEntry != null,
        onNavigateUp = { navController.navigateUp() }
    ) { modifier ->
        NavHost(
            navController = navController,
            startDestination = SolarScreen.Home.name,
            modifier = modifier
        ) {
            composable(route = SolarScreen.Home.name) {
                HomeScreen(
                    onAddSetupClick = { navController.navigate(SolarScreen.AddEntry.name) },
                    onViewHistoryClick = { navController.navigate(SolarScreen.History.name) },
                    onSdgInfoClick = { navController.navigate(SolarScreen.SdgInfo.name) },
                    onForecastClick = { navController.navigate(SolarScreen.SolarForecast.name) }, // Fixed: Parameter passed
                    onCloudClick = { navController.navigate(SolarScreen.CloudDashboard.name) }    // Fixed: Parameter passed
                )
            }

            composable(route = SolarScreen.AddEntry.name) {
                AddEntryScreen(
                    viewModel = viewModel,
                    onNavigateToResult = { navController.navigate(SolarScreen.Result.name) },
                    onCancel = { navController.navigateUp() }
                )
            }

            composable(route = SolarScreen.Result.name) {
                ResultScreen(
                    viewModel = viewModel,
                    onBackToHome = {
                        viewModel.resetCurrentData()
                        navController.popBackStack(SolarScreen.Home.name, inclusive = false)
                    },
                    onViewHistory = { navController.navigate(SolarScreen.History.name) }
                )
            }

            composable(route = SolarScreen.History.name) {
                HistoryScreen(viewModel = viewModel)
            }

            composable(route = SolarScreen.SdgInfo.name) {
                SdgInfoScreen()
            }

            // 🆕 SCREEN 6: SOLAR FORECAST (API + GPS)
            composable(route = SolarScreen.SolarForecast.name) {
                SolarForecastScreen()
            }

            // 🆕 SCREEN 7: CLOUD DASHBOARD (FIREBASE)
            composable(route = SolarScreen.CloudDashboard.name) {
                CloudDashboardScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolarProjectAppContent(
    currentScreen: SolarScreen,
    canNavigateBack: Boolean,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentScreen.title) },
                navigationIcon = {
                    if (canNavigateBack) {
                        IconButton(onClick = onNavigateUp) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SolarProjectAppPreview() {
    A214185_nazatulaini_project2aTheme { // Fixed: Unnecessary parentheses removed
        SolarProjectApp()
    }
}