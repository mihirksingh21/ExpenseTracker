package com.example.expensetracker.data.models

import java.time.LocalDateTime

data class Expense(
    val id: Int = 0,
    val userId: Int,
    val amount: Double,
    val category: ExpenseCategory,
    val description: String,
    val date: LocalDateTime = LocalDateTime.now()
)

enum class ExpenseCategory {
    FOOD,
    TRANSPORTATION,
    HOUSING,
    UTILITIES,
    ENTERTAINMENT,
    SHOPPING,
    HEALTHCARE,
    EDUCATION,
    OTHER
} 