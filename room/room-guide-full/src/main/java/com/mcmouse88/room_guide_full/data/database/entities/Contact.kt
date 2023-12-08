package com.mcmouse88.room_guide_full.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("last_name")
    val lastName: String,
    @ColumnInfo("phone_number")
    val phoneNumber: String,
    @[PrimaryKey(autoGenerate = true) ColumnInfo("id")]
    val id: Int = 0,
)
