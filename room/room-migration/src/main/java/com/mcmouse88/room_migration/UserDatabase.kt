package com.mcmouse88.room_migration

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [User::class, School::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = UserDatabase.Migration2To3::class)
    ]
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao

    /**
     * When the column in the table has been renamed, you need increment version the database and
     * create a class for migration likewise [Migration2To3]. As well Room has annotations
     * DeleteColumn, DeleteTable and etc. The renamed column doesn't need rename in ColumnInfo
     * into the entity class.
     */
    @RenameColumn(tableName = "user_table", fromColumnName = "created", toColumnName = "createdAt")
    class Migration2To3 : AutoMigrationSpec

    /**
     * When a new table has been added into database you need create an instance of abstract class
     * [Migration], then override fun migrate and write a new SQL query for create a new table.
     * AfterWards you need to add migration in the place where the database has been created.
     * example:
     * ```kotlin
     * val db = Room.databaseBuilder(
     *             applicationContext,
     *             UserDatabase::class.java,
     *             "users.db"
     *         )
     *             .addMigrations(UserDatabase.migration3To4)
     *             .build()
     * ```
     */
    companion object {
        val migration3To4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS school_table (school_name CHAR NOT NULL PRIMARY KEY)")
            }
        }
    }
}