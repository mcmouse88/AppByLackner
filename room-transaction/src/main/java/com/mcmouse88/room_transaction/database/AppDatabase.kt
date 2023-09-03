package com.mcmouse88.room_transaction.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mcmouse88.room_transaction.database.attendee.Attendee
import com.mcmouse88.room_transaction.database.attendee.AttendeeDao
import com.mcmouse88.room_transaction.database.event.Event
import com.mcmouse88.room_transaction.database.event.EventDao

@Database(entities = [Event::class, Attendee::class], version = 1)
 abstract class AppDatabase : RoomDatabase() {
     abstract fun eventDao(): EventDao
     abstract fun attendeeDao(): AttendeeDao
}