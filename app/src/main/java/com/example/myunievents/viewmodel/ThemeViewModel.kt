package com.example.myunievents.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {
    // State controlling light/dark mode
    var isDarkMode = mutableStateOf(false)
        private set

    fun toggleTheme() {
        isDarkMode.value = !isDarkMode.value
    }
}
