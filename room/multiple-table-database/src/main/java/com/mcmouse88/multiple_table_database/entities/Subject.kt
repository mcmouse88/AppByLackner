package com.mcmouse88.multiple_table_database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table")
data class Subject(
    @[PrimaryKey(autoGenerate = false) ColumnInfo("subject_name")]
    val subjectName: String
)
