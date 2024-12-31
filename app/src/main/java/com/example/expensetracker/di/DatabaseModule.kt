package com.example.expensetracker.di

import android.content.Context
import androidx.room.Room
import com.example.expensetracker.data.local.ExpenseDatabase
import com.example.expensetracker.data.local.dao.UserDao
import com.example.expensetracker.data.local.dao.ExpenseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideExpenseDatabase(
        @ApplicationContext context: Context
    ): ExpenseDatabase {
        return Room.databaseBuilder(
            context,
            ExpenseDatabase::class.java,
            "expense_tracker.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: ExpenseDatabase): UserDao {
        return database.userDao
    }

    @Provides
    @Singleton
    fun provideExpenseDao(database: ExpenseDatabase): ExpenseDao {
        return database.expenseDao
    }
} 