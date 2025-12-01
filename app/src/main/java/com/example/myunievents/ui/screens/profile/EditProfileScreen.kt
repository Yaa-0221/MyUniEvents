package com.example.myunievents.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun EditProfileScreen(navController: NavController) {

    val user = FirebaseAuth.getInstance().currentUser
    val firestore = FirebaseFirestore.getInstance()

    var name by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text("Edit Profile", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Display Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (user != null) {
                    firestore.collection("users")
                        .document(user.uid)
                        .update("displayName", name)
                }
                navController.popBackStack()
            }
        ) {
            Text("Save Profile")
        }
    }
}
