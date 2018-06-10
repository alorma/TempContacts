package com.alorma.tempcontacts.di

import android.content.Context
import androidx.room.Room
import com.alorma.tempcontacts.data.cache.AppDatabase
import com.alorma.tempcontacts.data.cache.ContactDao
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    companion object {
        const val IO = "io"
        const val MAIN = "main"
    }

    @Provides
    fun providesDatabase(): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "database")
                    .build()

    @Singleton
    @Provides
    fun providesContactsDao(appDatabase: AppDatabase): ContactDao = appDatabase.contactDao()

    @Named(IO)
    @Provides
    fun provideIOScheduler(): Scheduler = Schedulers.io()

    @Named(MAIN)
    @Provides
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    /*
    @Provides
    fun provideWorkManager(): WorkManager = WorkManager.getInstance()
    */
}