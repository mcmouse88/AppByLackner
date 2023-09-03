package com.mcmouse88.room_transaction.database.attendee

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AttendeeDao {

    @Insert
    suspend fun insertAttendee(attendee: Attendee)

    @Query("SELECT * FROM attendees WHERE eventId = :eventId")
    suspend fun getAttendeesForEvent(eventId: Long): List<Attendee>

    @Delete
    suspend fun deleteAttendee(attendee: Attendee)
}