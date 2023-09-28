package com.mcmouse88.multiple_table_database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @[PrimaryKey(autoGenerate = false) ColumnInfo("student_name")]
    val studentName: String,
    @ColumnInfo("semester")
    val semester: Int,
    @ColumnInfo("school_name")
    val schoolName: String
)
