package com.alorma.tempcontacts.data.framework

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.data.ActionLiveData
import com.alorma.tempcontacts.domain.model.Contact
import javax.inject.Inject

class ContactDataSource @Inject constructor(private val context: Context) {

    fun loadContact(contactUri: Uri): LiveData<Contact?> = ActionLiveData {
        context.contentResolver.query(contactUri, null, null, null, null)
                .takeIf { it.moveToFirst() }?.let {
                    val idIndex = it.getColumnIndex(ContactsContract.Contacts._ID)
                    val nameIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

                    val id = it.getString(idIndex)
                    val name = it.getString(nameIndex)

                    it.close()

                    Contact(id, name, 0)
                }
    }

    fun delete(androidId: String) {
        val uri = ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(),
                androidId.toLong()).build()

        delete(uri)
    }

    private fun delete(uri: Uri) {
        context.contentResolver.delete(uri, null, null)
    }
}