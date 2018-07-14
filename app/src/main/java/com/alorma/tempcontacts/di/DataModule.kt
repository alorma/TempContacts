package com.alorma.tempcontacts.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.alorma.tempcontacts.data.cache.AppDatabase
import com.alorma.tempcontacts.data.cache.DocumentsDao
import com.alorma.tempcontacts.data.framework.BaseCursor
import com.alorma.tempcontacts.data.framework.ContactCursor
import com.alorma.tempcontacts.data.framework.DocumentCursor
import com.alorma.tempcontacts.data.framework.ImageCursor
import dagger.Module
import dagger.Provides
import javax.inject.Named
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

    @Provides
    @Named(ContactCursor.NAME)
    fun provideContactCursor(context: Context): BaseCursor = ContactCursor(context)

    @Provides
    @Named(ImageCursor.NAME)
    fun provideImageCursor(context: Context): BaseCursor = ImageCursor(context)

    @Provides
    @Named(DocumentCursor.NAME)
    fun provideDocumentCursor(context: Context): BaseCursor = DocumentCursor(context)
}