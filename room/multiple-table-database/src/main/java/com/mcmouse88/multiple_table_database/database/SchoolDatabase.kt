package com.mcmouse88.multiple_table_database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mcmouse88.multiple_table_database.dao.SchoolDao
import com.mcmouse88.multiple_table_database.entities.Director
import com.mcmouse88.multiple_table_database.entities.School
import com.mcmouse88.multiple_table_database.entities.Student
import com.mcmouse88.multiple_table_database.entities.Subject
import com.mcmouse88.multiple_table_database.entities.relations.StudentSubjectCrossRef

@Database(
    entities = [
        School::class,
        Director::class,
        Student::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao: SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getInstance(context: Context): SchoolDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}