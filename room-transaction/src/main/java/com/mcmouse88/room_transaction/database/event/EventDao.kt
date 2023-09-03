package com.mcmouse88.room_transaction.database.event

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.mcmouse88.room_transaction.database.attendee.Attendee

@Dao
interface EventDao {

    @Insert
    suspend fun insertEvent(event: Event): Long

    @Query("SELECT * FROM events WHERE eventId = :id")
    suspend fun getEventById(id: Long): Event

    @Delete
    suspend fun deleteEvent(event: Event)

    @Insert
    suspend fun insertAttendees(attendees: List<Attendee>)

    @Transaction
    suspend fun insertWithAttendees(event: Event, attendees: List<Attendee>) {
        val eventId = insertEvent(event)
        val attendeesWithEventId = attendees.map {
            it.copy(eventId = eventId)
        }
        insertAttendees(attendeesWithEventId)
    }
}