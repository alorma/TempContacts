package com.alorma.tempcontacts.domain.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.CreateContact
import javax.inject.Inject
import com.alorma.tempcontacts.data.cache.ContactDataSource as Cache
import com.alorma.tempcontacts.data.framework.DocumentsDataSource as System

class DocumentsRepository @Inject constructor(
        private val system: System,
        private val cache: Cache) {

    fun load(): LiveData<List<AppDocument>> = cache.load()

    fun loadDocuments(): List<AppDocument> = cache.loadContacts()

    fun import(uri: Uri): LiveData<AppDocument?> = system.loadDocument(uri)

    fun delete(androidId: String) {
        system.delete(androidId)
        cache.delete(androidId)
    }

    fun exist(appDocument: AppDocument) : Boolean = system.exist(appDocument.androidId)

    fun create(createContact: CreateContact) = cache.save(createContact)
}