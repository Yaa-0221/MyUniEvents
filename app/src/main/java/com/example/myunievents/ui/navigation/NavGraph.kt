package com.example.myunievents.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myunievents.auth.LoginScreen
import com.example.myunievents.auth.RegisterScreen
import com.example.myunievents.ui.screens.details.EventDetailsScreen
import com.example.myunievents.ui.screens.home.HomeScreen
import com.example.myunievents.ui.screens.profile.EditProfileScreen
import com.example.myunievents.ui.screens.profile.ProfileScreen
import com.example.myunievents.ui.screens.saved.SavedScreen
import com.example.myunievents.viewmodel.ThemeViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    themeViewModel: ThemeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = Modifier.padding(paddingValues)
    ) {

        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }

        composable("home") { HomeScreen(navController) }
        composable("saved") { SavedScreen(navController) }

        composable("profile") {
            ProfileScreen(navController, themeViewModel)
        }

        composable("editProfile") {
            EditProfileScreen(navController)
        }

        composable("eventDetails/{eventId}") { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId")?.toInt()
            EventDetailsScreen(navController, eventId)
        }
    }
}
