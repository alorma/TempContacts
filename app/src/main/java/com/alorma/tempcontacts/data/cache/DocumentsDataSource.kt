package com.alorma.tempcontacts.data.cache

import android.util.Log
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

    fun get(androidId: String): AppDocument? = documentsDao.getById(androidId)?.let { mapper.map(it) }

    fun save(it: NewDocument) {
        Log.i("AlormaCreate", it.androidId)
        val byAndroidId = documentsDao.getById(it.androidId)
        if (byAndroidId == null) {
            documentsDao.insertAll(mapper.map(it))
        } else {
            documentsDao.update(mapper.map(it))
        }
    }

    fun delete(androidId: String) {
        documentsDao.delete(androidId)
    }
}