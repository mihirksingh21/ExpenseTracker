package com.example.expensetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    userPreferences: UserPreferences
) : ViewModel() {
    val isDarkTheme = userPreferences.isDarkTheme
} 