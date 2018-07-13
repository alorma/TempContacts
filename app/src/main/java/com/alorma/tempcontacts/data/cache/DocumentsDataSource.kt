package com.alorma.tempcontacts.data.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.NewDocument
import javax.inject.Inject

class DocumentsDataSource @Inject constructor(
        private val documentsDao: DocumentsDao,
        private val mapper: DocumentMapper) {

    fun load(): LiveData<List<AppDocument>> = Transformations.map(documentsDao.get()) {
        it.map { mapper.map(it) }
    }

    fun loadContacts(): List<AppDocument> = documentsDao.getList().map { mapper.map(it) }

    fun get(androidId: String): LiveData<AppDocument> = Transformations.map(documentsDao.findById(androidId)) {
        mapper.map(it)
    }

    fun save(it: NewDocument) {
        val byAndroidId = documentsDao.getById(it.androidId)
        if (byAndroidId == null) {
            documentsDao.insertAll(mapper.map(it))
        } else {
            documentsDao.update(mapper.map(it))
        }
    }

    fun delete(androidId: String) {
        //documentsDao.delete(androidId)
    }
}