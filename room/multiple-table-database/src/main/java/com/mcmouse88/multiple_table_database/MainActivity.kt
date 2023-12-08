package com.mcmouse88.multiple_table_database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.mcmouse88.multiple_table_database.database.SchoolDatabase
import com.mcmouse88.multiple_table_database.entities.Director
import com.mcmouse88.multiple_table_database.entities.School
import com.mcmouse88.multiple_table_database.entities.Student
import com.mcmouse88.multiple_table_database.entities.Subject
import com.mcmouse88.multiple_table_database.entities.relations.StudentSubjectCrossRef
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity.kt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = SchoolDatabase.getInstance(this).schoolDao
        lifecycleScope.launch {
            createDirectorList().forEach { dao.insertDirector(it) }
            createSchoolList().forEach { dao.insertSchool(it) }
            createStudentList().forEach { dao.insertStudent(it) }
            createSubjectList().forEach { dao.insertSubject(it) }
            createStudentSubjectRelations().forEach { dao.insertStudentSubjectCrossRef(it) }

            val schoolWithDirector = dao.getSchoolAndDirectorWithSchoolName("Kotlin School")
            Log.e(TAG, "schoolWithDirector: ${schoolWithDirector.joinToString()}")

            val schoolWithStudents = dao.getSchoolWithStudents("Kotlin School")
            Log.e(TAG, "schoolWithStudents: ${schoolWithStudents.joinToString()}")

            val studentsOfSubject = dao.getStudentsOfSubject("Dating for programmers")
            Log.e(TAG, "studentsOfSubject: ${studentsOfSubject.joinToString()}")

            val subjectsOfStudent = dao.getSubjectsOfStudent("Hom Tanks")
            Log.e(TAG, "subjectsOfStudent: ${subjectsOfStudent.joinToString()}")
        }
    }

    private fun createDirectorList(): List<Director> {
        return listOf(
            Director("Mike Litoris", "Jake Wharton School"),
            Director("Jack Goff", "Kotlin School"),
            Director("Chris P. Chicken", "JetBrains School")
        )
    }

    private fun createSchoolList(): List<School> {
        return listOf(
            School("Jake Wharton School"),
            School("Kotlin School"),
            School("JetBrains School")
        )
    }

    private fun createStudentList(): List<Student> {
        return listOf(
            Student("Beff Jezos", 2, "Kotlin School"),
            Student("Mark Suckerberg", 5, "Jake Wharton School"),
            Student("Gill Bates", 8, "Kotlin School"),
            Student("Donny Jepp", 1, "Kotlin School"),
            Student("Hom Tanks", 2, "JetBrains School")
        )
    }

    private fun createSubjectList(): List<Subject> {
        return listOf(
            Subject("Dating for programmers"),
            Subject("Avoiding depression"),
            Subject("Bug Fix Meditation"),
            Subject("Logcat for Newbies"),
            Subject("How to use Google")
        )
    }

    private fun createStudentSubjectRelations(): List<StudentSubjectCrossRef> {
        return listOf(
            StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
            StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
            StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
            StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
            StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
            StudentSubjectCrossRef("Gill Bates", "How to use Google"),
            StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
            StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
            StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
        )
    }
}