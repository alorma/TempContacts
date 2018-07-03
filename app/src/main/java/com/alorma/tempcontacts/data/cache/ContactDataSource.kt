package com.alorma.tempcontacts.data.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.model.CreateContact
import javax.inject.Inject

class ContactDataSource @Inject constructor(
        private val contactDao: ContactDao,
        private val mapper: ContactMapper) {

    fun load(): LiveData<List<Contact>> = Transformations.map(contactDao.get()) {
        it.map { mapper.map(it) }
    }

    fun get(androidId: String): LiveData<Contact> = Transformations.map(contactDao.findById(androidId)) {
        mapper.map(it)
    }

    fun save(it: CreateContact) {
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