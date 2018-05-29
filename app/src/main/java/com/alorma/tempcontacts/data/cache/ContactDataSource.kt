package com.alorma.tempcontacts.data.cache

import com.alorma.tempcontacts.domain.model.Contact
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

class ContactDataSource @Inject constructor(
        private val contactDao: ContactDao,
        private val mapper: ContactMapper) {

    operator fun invoke(): Maybe<List<Contact>> = contactDao.get().map { it.map { mapper(it) } }

    operator fun invoke(id: Long): Maybe<Contact> = contactDao.findById(id).map { mapper(it) }

    operator fun invoke(id: String): Maybe<Contact> = contactDao.findByAndroidId(id).map { mapper(it) }

    fun save(it: Contact) {
        val byAndroidId = contactDao.getByAndroidId(it.androidId)
        if (byAndroidId == null) {
            contactDao.insertAll(mapper(it))
        } else {
            contactDao.update(mapper(it.copy(id = byAndroidId.id)))
        }
    }

    fun delete(it: Contact): Completable = Completable.fromAction {
        contactDao.delete(mapper(it))
    }
}