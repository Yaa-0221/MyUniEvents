package com.example.myunievents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.myunievents.ui.navigation.BottomBar
import com.example.myunievents.ui.navigation.NavGraph
import com.example.myunievents.ui.theme.MyUniEventsTheme
import com.example.myunievents.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // 监听 dark mode 状态
            val isDark by themeViewModel.isDarkTheme.collectAsState()

            MyUniEventsTheme(darkTheme = isDark) {
                MainScreen(themeViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            paddingValues = innerPadding,
            themeViewModel = themeViewModel
        )
    }
}
