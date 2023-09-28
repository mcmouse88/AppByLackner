package com.mcmouse88.multiple_table_database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school_table")
data class School(
    @[PrimaryKey(autoGenerate = false) ColumnInfo("school_name")]
    val schoolName: String
)