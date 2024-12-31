package com.example.expensetracker.data.repository

import com.example.expensetracker.data.local.dao.ExpenseDao
import com.example.expensetracker.data.local.entity.ExpenseEntity
import com.example.expensetracker.data.models.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
) {
    fun getExpensesByUser(userId: Int): Flow<List<Expense>> {
        return expenseDao.getExpensesByUser(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    fun getExpensesByDateRange(
        userId: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Expense>> {
        return expenseDao.getExpensesByDateRange(userId, startDate, endDate).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun addExpense(expense: Expense): Result<Unit> {
        return try {
            expenseDao.insertExpense(expense.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTotalExpenseInRange(
        userId: Int,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Double {
        return expenseDao.getTotalExpenseInRange(userId, startDate, endDate) ?: 0.0
    }
}

private fun ExpenseEntity.toDomain() = Expense(
    id = id,
    userId = userId,
    amount = amount,
    category = category,
    description = description,
    date = date
)

private fun Expense.toEntity() = ExpenseEntity(
    id = id,
    userId = userId,
    amount = amount,
    category = category,
    description = description,
    date = date
) 