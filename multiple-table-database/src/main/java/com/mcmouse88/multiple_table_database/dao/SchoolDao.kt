package com.mcmouse88.multiple_table_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mcmouse88.multiple_table_database.entities.Director
import com.mcmouse88.multiple_table_database.entities.School
import com.mcmouse88.multiple_table_database.entities.Student
import com.mcmouse88.multiple_table_database.entities.Subject
import com.mcmouse88.multiple_table_database.entities.relations.SchoolAndDirectorRelation
import com.mcmouse88.multiple_table_database.entities.relations.SchoolWithStudents
import com.mcmouse88.multiple_table_database.entities.relations.StudentSubjectCrossRef
import com.mcmouse88.multiple_table_database.entities.relations.StudentWithSubjects
import com.mcmouse88.multiple_table_database.entities.relations.SubjectWithStudents

@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Transaction
    @Query("SELECT * FROM school_table WHERE school_name = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String): List<SchoolAndDirectorRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Transaction
    @Query("SELECT * FROM school_table WHERE school_name = :schoolName")
    suspend fun getSchoolWithStudents(schoolName: String): List<SchoolWithStudents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)

    @Transaction
    @Query("SELECT * FROM subject_table WHERE subject_name = :subjectName")
    suspend fun getStudentsOfSubject(subjectName: String): List<SubjectWithStudents>

    @Transaction
    @Query("SELECT * FROM student_table WHERE student_name = :studentName")
    suspend fun getSubjectsOfStudent(studentName: String): List<StudentWithSubjects>
}