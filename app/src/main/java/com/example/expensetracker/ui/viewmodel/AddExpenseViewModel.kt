package com.example.expensetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.models.Expense
import com.example.expensetracker.data.models.ExpenseCategory
import com.example.expensetracker.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddExpenseUiState>(AddExpenseUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun addExpense(amount: Double, description: String, category: ExpenseCategory) {
        viewModelScope.launch {
            _uiState.value = AddExpenseUiState.Loading

            // TODO: Get actual user ID from UserRepository or UserPreferences
            val userId = 1
            
            val expense = Expense(
                userId = userId,
                amount = amount,
                description = description,
                category = category
            )

            expenseRepository.addExpense(expense)
                .onSuccess {
                    _uiState.value = AddExpenseUiState.Success
                }
                .onFailure { error ->
                    _uiState.value = AddExpenseUiState.Error(error.message ?: "Failed to add expense")
                }
        }
    }
}

sealed interface AddExpenseUiState {
    object Initial : AddExpenseUiState
    object Loading : AddExpenseUiState
    object Success : AddExpenseUiState
    data class Error(val message: String) : AddExpenseUiState
} 