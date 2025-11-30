package com.example.myunievents.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : NavigationItem("home", "Home", Icons.Default.Home)
    object Saved : NavigationItem("saved", "Saved", Icons.Default.Star)
    object Profile : NavigationItem("profile", "Profile", Icons.Default.Person)
}
