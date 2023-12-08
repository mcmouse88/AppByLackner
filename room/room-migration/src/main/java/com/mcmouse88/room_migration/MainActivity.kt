package com.mcmouse88.room_migration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.room.Room

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "users.db"
        )
            .addMigrations(UserDatabase.migration3To4)
            .build()
    }
}