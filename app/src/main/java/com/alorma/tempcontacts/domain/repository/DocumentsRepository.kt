package com.alorma.tempcontacts.domain.repository

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.NewDocument
import com.alorma.tempcontacts.domain.model.Type
import javax.inject.Inject
import com.alorma.tempcontacts.data.cache.DocumentsDataSource as Cache
import com.alorma.tempcontacts.data.framework.DocumentsDataSource as System

class DocumentsRepository @Inject constructor(
        private val system: System,
        private val cache: Cache) {

    fun load(): LiveData<List<AppDocument>> = cache.load()

    fun loadDocument(androidId: String): AppDocument? = cache.get(androidId)

    fun loadDocuments(): List<AppDocument> = cache.loadContacts()

    fun import(uri: Uri): LiveData<AppDocument?> = system.loadDocument(uri)

    fun delete(androidId: String, uri: Uri, type: Type) {
        system.delete(uri, type)
        cache.delete(androidId)
    }

    fun syncLocal(androidId: String, uri: Uri, type: Type) {
        try {
            if (!exist(uri, type)) {
                cache.delete(androidId)
            }
        } catch (e: Exception) {
            Log.e("AlormaSync", e.message, e)
        }
    }

    private fun exist(uri: Uri, type: Type): Boolean = system.exist(uri, type)

    fun create(newDocument: NewDocument) = cache.save(newDocument)
}