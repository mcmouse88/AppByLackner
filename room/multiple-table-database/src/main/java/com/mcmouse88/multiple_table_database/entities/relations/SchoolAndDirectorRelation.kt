package com.mcmouse88.multiple_table_database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.mcmouse88.multiple_table_database.entities.Director
import com.mcmouse88.multiple_table_database.entities.School

data class SchoolAndDirectorRelation(
    @Embedded val school: School,
    @Relation(
        parentColumn = "school_name",
        entityColumn = "school_name"
    )
    val director: Director
)