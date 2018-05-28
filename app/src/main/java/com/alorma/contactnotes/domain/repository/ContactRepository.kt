package com.alorma.contactnotes.domain.repository

import android.net.Uri
import com.alorma.contactnotes.data.framework.ContactDataSource as System
import com.alorma.contactnotes.domain.model.Contact
import io.reactivex.Maybe
import javax.inject.Inject
import com.alorma.contactnotes.data.cache.ContactDataSource as Cache

class ContactRepository @Inject constructor(
        private val system: System,
        private val cache: Cache) {

    operator fun invoke(): Maybe<List<Contact>> = cache()

    operator fun invoke(uri: Uri): Maybe<Contact> = system.invoke(uri)
            .doOnSuccess { cache.save(it) }

    operator fun invoke(id: Long): Maybe<Contact> = cache(id)
}