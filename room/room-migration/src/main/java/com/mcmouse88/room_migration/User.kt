package com.mcmouse88.room_migration

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * When the new column has been added into the table, in order to migrate in a new version the
 * database it needs to define a default value into [ColumnInfo] and add an autoMigration value
 * into @Database where to show the database versions ***from*** and ***to*** you need to migrate.
 * As well it needs to export schemas the both versions
 */
@Entity(tableName = "user_table")
data class User(
    @[PrimaryKey(autoGenerate = false) ColumnInfo("email")]
    val email: String,
    @ColumnInfo("username")
    val username: String,
    @ColumnInfo("created", defaultValue = "0")
    val createdAt: Long = System.currentTimeMillis()
)
