package com.example.expensetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                userPreferences.isDarkTheme,
                userPreferences.currency
            ) { isDarkTheme, currency ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isDarkTheme = isDarkTheme,
                        selectedCurrency = currency
                    )
                }
            }.collect()
        }
    }

    fun updateDarkTheme(isDark: Boolean) {
        viewModelScope.launch {
            userPreferences.updateDarkTheme(isDark)
        }
    }

    fun updateCurrency(currency: String) {
        viewModelScope.launch {
            userPreferences.updateCurrency(currency)
        }
    }

    fun showCurrencyDialog() {
        _uiState.update { it.copy(showCurrencyDialog = true) }
    }

    fun hideCurrencyDialog() {
        _uiState.update { it.copy(showCurrencyDialog = false) }
    }

    fun logout() {
        viewModelScope.launch {
            userPreferences.clearPreferences()
        }
    }
}

data class SettingsUiState(
    val isDarkTheme: Boolean = false,
    val selectedCurrency: String = "USD",
    val showCurrencyDialog: Boolean = false
) 