package com.mcmouse88.room_transaction.database

import androidx.room.Embedded
import androidx.room.Relation
import com.mcmouse88.room_transaction.database.attendee.Attendee
import com.mcmouse88.room_transaction.database.event.Event

data class EventWithAttendees(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "eventId",
        entityColumn = "eventId"
    )
    val attendees: List<Attendee>
)
