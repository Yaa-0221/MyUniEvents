package com.example.myunievents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myunievents.ui.navigation.NavGraph
import com.example.myunievents.ui.navigation.BottomBar
import com.example.myunievents.ui.theme.MyUniEventsTheme
import com.example.myunievents.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyUniEventsTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // ⭐ 创建 Dark Mode ViewModel
    val themeViewModel: ThemeViewModel = viewModel()

    Scaffold(
        bottomBar = {
            // ⭐ 只有在登录后才显示 BottomBar
            val current = navController.currentBackStackEntry?.destination?.route
            if (current != "login" && current != "register") {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            paddingValues = innerPadding,
            themeViewModel = themeViewModel   // ⭐ 必须传入
        )
    }
}


