package com.example.myunievents.ui.screens.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myunievents.ui.screens.home.EventCard
import com.example.myunievents.viewmodel.EventsViewModel
import com.example.myunievents.viewmodel.EventsViewModelFactory


@Composable
fun SavedScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel: EventsViewModel = viewModel(factory = EventsViewModelFactory(context))

    val savedEvents by viewModel.savedEvents.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("Saved Events", style = MaterialTheme.typography.titleLarge)

        LazyColumn {
            items(savedEvents) { event ->
                EventCard(
                    title = event.title,
                    date = event.date,
                    location = event.location,
                    onClick = {
                        navController.navigate("eventDetails/${event.id}")
                    }
                )
            }
        }
    }
}

