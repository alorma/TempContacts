package com.alorma.tempcontacts.domain.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.model.CreateContact
import io.reactivex.Completable
import javax.inject.Inject
import com.alorma.tempcontacts.data.cache.ContactDataSource as Cache
import com.alorma.tempcontacts.data.framework.ContactDataSource as System

class ContactRepository @Inject constructor(
        private val system: System,
        private val cache: Cache) {

    fun load(): LiveData<List<Contact>> = cache.load()

    fun import(uri: Uri): LiveData<Contact?> = system.loadContact(uri)

    fun delete(androidId: String) {
        system.delete(androidId)
        cache.delete(androidId)
    }

    fun delete(it: Uri) = system.delete(it)

    fun create(createContact: CreateContact) = cache.save(createContact)
}