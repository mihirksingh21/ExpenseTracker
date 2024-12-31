package com.example.expensetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.ui.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Theme Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dark Theme")
                Switch(
                    checked = uiState.isDarkTheme,
                    onCheckedChange = { viewModel.updateDarkTheme(it) }
                )
            }

            // Currency Selection
            OutlinedButton(
                onClick = { viewModel.showCurrencyDialog() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Currency: ${uiState.selectedCurrency}")
            }

            if (uiState.showCurrencyDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.hideCurrencyDialog() },
                    title = { Text("Select Currency") },
                    text = {
                        Column {
                            listOf("USD", "EUR", "GBP", "INR").forEach { currency ->
                                TextButton(
                                    onClick = {
                                        viewModel.updateCurrency(currency)
                                        viewModel.hideCurrencyDialog()
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(currency)
                                }
                            }
                        }
                    },
                    confirmButton = {}
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Logout Button
            Button(
                onClick = {
                    viewModel.logout()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Logout")
            }
        }
    }
} 