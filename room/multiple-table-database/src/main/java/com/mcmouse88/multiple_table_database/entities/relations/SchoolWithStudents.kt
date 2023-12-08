package com.mcmouse88.multiple_table_database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.mcmouse88.multiple_table_database.entities.School
import com.mcmouse88.multiple_table_database.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "school_name",
        entityColumn = "school_name"
    )
    val students: List<Student>
)
