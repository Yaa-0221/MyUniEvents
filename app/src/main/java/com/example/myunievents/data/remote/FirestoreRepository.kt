package com.example.myunievents.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.example.myunievents.data.local.Event
import kotlinx.coroutines.tasks.await

class FirestoreRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    suspend fun getAllEvents(): List<Event> {
        return try {
            db.collection("events")
                .get()
                .await()
                .documents
                .mapNotNull { doc ->
                    doc.toObject(Event::class.java)?.copy(id = doc.id.hashCode())
                }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun addEvent(event: Event) {
        db.collection("events")
            .add(event)
            .await()
    }
}
