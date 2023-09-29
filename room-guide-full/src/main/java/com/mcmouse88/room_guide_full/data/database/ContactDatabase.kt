package com.mcmouse88.room_guide_full.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mcmouse88.room_guide_full.data.database.dao.ContactDao
import com.mcmouse88.room_guide_full.data.database.entities.Contact

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false
)
abstract class ContactDatabase : RoomDatabase() {
    abstract val dao: ContactDao
}