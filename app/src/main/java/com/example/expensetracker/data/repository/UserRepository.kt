package com.example.expensetracker.data.repository

import com.example.expensetracker.data.local.dao.UserDao
import com.example.expensetracker.data.local.entity.UserEntity
import com.example.expensetracker.data.models.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun registerUser(user: User): Result<Unit> {
        return try {
            val existingUser = userDao.getUserByEmail(user.email)
            if (existingUser != null) {
                Result.failure(Exception("User with this email already exists"))
            } else {
                userDao.insertUser(user.toEntity())
                Result.success(Unit)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginUser(email: String, password: String): Result<User> {
        return try {
            val user = userDao.getUser(email, password)
            if (user != null) {
                Result.success(user.toDomain())
            } else {
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private fun User.toEntity() = UserEntity(
    id = id,
    username = username,
    email = email,
    password = password,
    monthlyIncome = monthlyIncome,
    dailyExpenseLimit = dailyExpenseLimit,
    savings = savings
)

private fun UserEntity.toDomain() = User(
    id = id,
    username = username,
    email = email,
    password = password,
    monthlyIncome = monthlyIncome,
    dailyExpenseLimit = dailyExpenseLimit,
    savings = savings
) 