package com.alorma.contactnotes.data.framework

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import com.alorma.contactnotes.domain.model.Contact
import io.reactivex.Single
import javax.inject.Inject

class SystemContactDataSource @Inject constructor(private val context: Context) {

    operator fun invoke(contactUri: Uri): Single<Contact> {
        return Single.fromCallable { getContact(contactUri) }
    }

    private fun getContact(contactUri: Uri): Contact = Contact(ContentUris.parseId(contactUri))

}