package com.alorma.tempcontacts.data.framework

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.extensions.queryExist
import com.alorma.tempcontacts.extensions.queryFirstLive
import javax.inject.Inject

class ContactDataSource @Inject constructor(private val context: Context) {

    fun loadContact(contactUri: Uri): LiveData<Contact?> = context.queryFirstLive(contactUri) {
        val idIndex = getColumnIndex(ContactsContract.Contacts._ID)
        val nameIndex = getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

        val id = getString(idIndex)
        val name = getString(nameIndex)

        Contact(id, name, 0)
    }

    fun delete(androidId: String) {
        val uri = ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(),
                androidId.toLong()).build()

        delete(uri)
    }

    private fun delete(uri: Uri) {
        context.contentResolver.delete(uri, null, null)
    }

    fun exist(androidId: String): Boolean {
        val uri = ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(),
                androidId.toLong()).build()

        return context.queryExist(uri)
    }
}