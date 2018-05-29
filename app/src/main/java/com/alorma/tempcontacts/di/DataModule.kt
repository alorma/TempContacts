package com.alorma.tempcontacts.di

import android.content.Context
import androidx.room.Room
import com.alorma.tempcontacts.data.cache.AppDatabase
import com.alorma.tempcontacts.data.cache.ContactDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    @Provides
    fun proviesDatabase(): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "database")
                    .build()

    @Singleton
    @Provides
    fun proideContactsDao(appDatabase: AppDatabase): ContactDao = appDatabase.contactDao()
}