package com.mcmouse88.room_transaction.database.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val eventId: Long = 0L,
    val title: String,
    val description: String,
    val timestamp: Long
)