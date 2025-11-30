package com.example.myunievents.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myunievents.data.local.AppDatabase
import com.example.myunievents.data.local.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventsViewModel(private val context: Context) : ViewModel() {

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
            eventDao.insertEvent(event)
            _savedEvents.value = eventDao.getAllEvents()
        }
    }

    fun getEventById(id: Int?): Event? {
        return _savedEvents.value.find { it.id == id }
    }

    fun getFirebaseUpdates(eventId: Int) {
        // This will be implemented later
    }
}

class EventsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventsViewModel(context) as T
    }
}
