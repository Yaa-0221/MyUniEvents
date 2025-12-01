package com.example.myunievents.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myunievents.viewmodel.EventsViewModel
import com.example.myunievents.viewmodel.EventsViewModelFactory

@Composable
fun EventDetailsScreen(
    navController: NavController,
    eventId: Int?
) {
    val context = LocalContext.current
    val viewModel: EventsViewModel = viewModel(factory = EventsViewModelFactory(context))

    val event = viewModel.getEventById(eventId)

    if (event == null) {
        Text("Loading event...")
        return
    }

    Column(
        Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(event.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))

        Text("${event.date} • ${event.time}")
        Text(event.location)

        Spacer(Modifier.height(12.dp))
        Divider()
        Spacer(Modifier.height(12.dp))

        Text("Description", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))

        Text(event.description)

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.saveEvent(event) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Event")
        }
    }
}
