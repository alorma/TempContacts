package com.alorma.tempcontacts.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(DocumentEntity::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): DocumentsDao
}