package com.example.myunievents.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myunievents.data.local.AppDatabase
import com.example.myunievents.data.local.Event
import com.example.myunievents.data.remote.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventsViewModel(private val context: Context) : ViewModel() {

    private val firestore = FirestoreRepository()

    fun loadEventsFromFirestore() {
        viewModelScope.launch {
            val firestoreEvents = firestore.getAllEvents()
            _savedEvents.value = firestoreEvents   
        }
    }

    fun addEventToFirestore(event: Event) {
        viewModelScope.launch {
            firestore.addEvent(event)
            loadEventsFromFirestore()
        }
    }
    private val eventDao = AppDatabase.getDatabase(context).eventDao()

    private val _savedEvents = MutableStateFlow<List<Event>>(emptyList())
    val savedEvents = _savedEvents.asStateFlow()

    init {
        viewModelScope.launch {
            _savedEvents.value = eventDao.getAllEvents()
        }
    }

    fun saveEvent(event: Event) {
        viewModelScope.launch {
            // Save to Room
            eventDao.insertEvent(event)
            _savedEvents.value = eventDao.getAllEvents()

            // Save to Firebase
            val db = FirebaseFirestore.getInstance()
            db.collection("events")
                .add(event)
        }
    }


    fun getEventById(id: Int?): Event? {
        return _savedEvents.value.find { it.id == id }
    }
}

class EventsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventsViewModel(context) as T
    }
}
