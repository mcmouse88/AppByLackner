package com.mcmouse88.room_transaction.database.attendee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mcmouse88.room_transaction.database.event.Event

@Entity(
    tableName = "attendees",
    foreignKeys = [
        ForeignKey(
            entity = Event::class,
            parentColumns = ["eventId"],
            childColumns = ["eventId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Attendee(
    @PrimaryKey(autoGenerate = true) val attendeeId: Long = 0L,
    val eventId: Long,
    val name: String,
    val profilePictureUrl: String?
)