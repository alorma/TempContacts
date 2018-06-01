package com.alorma.tempcontacts.domain.repository

import android.net.Uri
import com.alorma.tempcontacts.domain.model.Contact
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject
import com.alorma.tempcontacts.data.cache.ContactDataSource as Cache
import com.alorma.tempcontacts.data.framework.ContactDataSource as System

class ContactRepository @Inject constructor(
        private val system: System,
        private val cache: Cache) {

    operator fun invoke(): Maybe<List<Contact>> = cache()

    fun import(uri: Uri): Maybe<Contact> = system.invoke(uri)

    operator fun invoke(id: Long): Maybe<Contact> = cache(id)

    fun delete(it: Contact): Completable = system.delete(it).concatWith(cache.delete(it))

    fun delete(it: Uri): Completable = system.delete(it)
}