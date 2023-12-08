package com.mcmouse88.multiple_table_database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "director_table")
data class Director(
    @[PrimaryKey(autoGenerate = false) ColumnInfo("director_name")]
    val directorName: String,
    @ColumnInfo("school_name")
    val schoolName: String
)