package com.example.expensetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.models.Expense
import com.example.expensetracker.data.repository.ExpenseRepository
import com.example.expensetracker.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            // TODO: Get actual user ID from UserRepository or UserPreferences
            val userId = 1
            
            // Get current month's date range
            val now = LocalDateTime.now()
            val startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth())
            val endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth())
            
            // Load expenses for current month
            expenseRepository.getExpensesByDateRange(userId, startOfMonth, endOfMonth)
                .collect { expenses ->
                    val totalSpent = expenses.sumOf { it.amount }
                    val dailyLimit = calculateDailyLimit(totalSpent, now.dayOfMonth)
                    
                    _uiState.update { currentState ->
                        currentState.copy(
                            recentExpenses = expenses,
                            totalSpent = totalSpent,
                            dailyLimit = dailyLimit
                        )
                    }
                }
        }
    }

    private fun calculateDailyLimit(totalSpent: Double, currentDay: Int): Double {
        // TODO: Get monthly income from UserRepository
        val monthlyIncome = 5000.0 // Placeholder value
        val daysInMonth = LocalDateTime.now().month.length(false)
        val remainingDays = daysInMonth - currentDay + 1
        
        return (monthlyIncome - totalSpent) / remainingDays
    }
}

data class HomeUiState(
    val dailyLimit: Double = 0.0,
    val totalSpent: Double = 0.0,
    val savings: Double = 0.0,
    val recentExpenses: List<Expense> = emptyList()
) 