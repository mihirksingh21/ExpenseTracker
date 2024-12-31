package com.example.expensetracker.data.local

import androidx.room.TypeConverter
import com.example.expensetracker.data.models.ExpenseCategory
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(ZoneOffset.UTC)
    }

    @TypeConverter
    fun fromCategory(value: ExpenseCategory): String {
        return value.name
    }

    @TypeConverter
    fun toCategory(value: String): ExpenseCategory {
        return ExpenseCategory.valueOf(value)
    }
} 