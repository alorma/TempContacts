package com.alorma.tempcontacts.data.cache

import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.model.CreateContact
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

class ContactDataSource @Inject constructor(
        private val contactDao: ContactDao,
        private val mapper: ContactMapper) {

    operator fun invoke(): Maybe<List<Contact>> = contactDao.get().map { it.map { mapper.map(it) } }

    operator fun invoke(id: String): Maybe<Contact> = contactDao.findById(id).map { mapper.map(it) }

    fun save(it: CreateContact): Completable = Completable.fromAction {
        val byAndroidId = contactDao.getById(it.androidId)
        if (byAndroidId == null) {
            contactDao.insertAll(mapper.map(it))
        } else {
            contactDao.update(mapper.map(it))
        }
    }

    fun delete(androidId: String) {
        contactDao.delete(androidId)
    }
}