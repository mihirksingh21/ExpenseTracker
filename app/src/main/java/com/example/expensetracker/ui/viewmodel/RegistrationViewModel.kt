package com.example.expensetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.models.User
import com.example.expensetracker.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun register(username: String, email: String, password: String, monthlyIncome: Double) {
        viewModelScope.launch {
            _uiState.value = RegistrationUiState.Loading
            
            val user = User(
                username = username,
                email = email,
                password = password,
                monthlyIncome = monthlyIncome,
                dailyExpenseLimit = monthlyIncome / 30 // Simple calculation
            )
            
            userRepository.registerUser(user)
                .onSuccess {
                    _uiState.value = RegistrationUiState.Success
                }
                .onFailure { error ->
                    _uiState.value = RegistrationUiState.Error(error.message ?: "Registration failed")
                }
        }
    }
}

sealed interface RegistrationUiState {
    object Initial : RegistrationUiState
    object Loading : RegistrationUiState
    object Success : RegistrationUiState
    data class Error(val message: String) : RegistrationUiState
} 