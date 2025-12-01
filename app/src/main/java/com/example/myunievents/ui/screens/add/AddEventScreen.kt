package com.example.myunievents.ui.screens.add

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myunievents.viewmodel.EventsViewModel
import com.example.myunievents.viewmodel.EventsViewModelFactory
import com.example.myunievents.data.local.Event

@Composable
fun AddEventScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel: EventsViewModel = viewModel(factory = EventsViewModelFactory(context))

    var title by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("Add New Event", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") })
        OutlinedTextField(value = time, onValueChange = { time = it }, label = { Text("Time") })
        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    val event = Event(
                        title = title,
                        date = date,
                        time = time,
                        location = location,
                        description = description
                    )

                    viewModel.saveEvent(event)
                    navController.navigate("home")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Event")
        }
    }
}
