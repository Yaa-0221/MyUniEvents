package com.example.myunievents.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myunievents.auth.AuthRepository
import com.example.myunievents.viewmodel.ThemeViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel,
    authRepository: AuthRepository = AuthRepository()
) {
    Column(
        Modifier.fillMaxSize().padding(20.dp)
    ) {
        // avatar
        Icon(
            Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(90.dp)
        )

        Spacer(Modifier.height(10.dp))
        Text("Username", fontSize = 22.sp)

        Spacer(Modifier.height(20.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Dark Mode")
            Switch(
                checked = themeViewModel.isDarkMode.value,
                onCheckedChange = { themeViewModel.isDarkMode.value = it }
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = { navController.navigate("editProfile") }) {
            Text("Edit Profile")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            authRepository.logout()
            navController.navigate("login") {
                popUpTo(0)
            }
        }) {
            Text("Logout")
        }
    }
}
