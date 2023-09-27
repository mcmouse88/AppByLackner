package com.mcmouse88.multiple_table_database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.mcmouse88.multiple_table_database.entities.Student
import com.mcmouse88.multiple_table_database.entities.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "student_name",
        entityColumn = "subject_name",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)
