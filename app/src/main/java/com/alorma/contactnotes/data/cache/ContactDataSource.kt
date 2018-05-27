package com.alorma.contactnotes.data.cache

import com.alorma.contactnotes.domain.model.Contact
import io.reactivex.Maybe
import javax.inject.Inject

class ContactDataSource @Inject constructor(
        private val contactDao: ContactDao,
        private val mapper: ContactMapper) {

    operator fun invoke(): Maybe<List<Contact>> = contactDao.get().map { it.map { mapper(it) } }

    operator fun invoke(id: Long): Maybe<Contact> = contactDao.findById(id).map { mapper(it) }

    fun save(it: Contact) = contactDao.insertAll(mapper(it))
}