package com.mcmouse88.multiple_table_database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.mcmouse88.multiple_table_database.entities.Student
import com.mcmouse88.multiple_table_database.entities.Subject

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subject_name",
        entityColumn = "student_name",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)
