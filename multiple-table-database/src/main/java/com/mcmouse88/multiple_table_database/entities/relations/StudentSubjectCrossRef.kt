package com.mcmouse88.multiple_table_database.entities.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "student_subject_relation_table",
    primaryKeys = ["student_name", "subject_name"]
)
data class StudentSubjectCrossRef(
    @ColumnInfo("student_name")
    val studentName: String,
    @ColumnInfo("subject_name")
    val subjectName: String,
)
