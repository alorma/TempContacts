package com.alorma.tempcontacts.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.alorma.tempcontacts.data.cache.AppDatabase
import com.alorma.tempcontacts.data.cache.DocumentsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    @Provides
    fun providesDatabase(): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "database")
                    .build()

    @Singleton
    @Provides
    fun providesContactsDao(appDatabase: AppDatabase): DocumentsDao = appDatabase.contactDao()

    @Provides
    fun provideWorkManager(): WorkManager? = WorkManager.getInstance()
}