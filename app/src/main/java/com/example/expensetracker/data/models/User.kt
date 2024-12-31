package com.example.expensetracker.data.models

data class User(
    val id: Int = 0,
    val username: String,
    val email: String,
    val password: String,
    val monthlyIncome: Double = 0.0,
    val dailyExpenseLimit: Double = 0.0,
    val savings: Double = 0.0
) 