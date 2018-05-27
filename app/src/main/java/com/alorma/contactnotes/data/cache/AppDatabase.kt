package com.alorma.contactnotes.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(ContactEntity::class)], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}