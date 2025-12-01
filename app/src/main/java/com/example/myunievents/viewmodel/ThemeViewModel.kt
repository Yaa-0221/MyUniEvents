package com.example.myunievents.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel : ViewModel() {

    // false = light, true = dark
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme = _isDarkTheme.asStateFlow()

    fun setDarkTheme(enabled: Boolean) {
        _isDarkTheme.value = enabled
    }

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }
}
