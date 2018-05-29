package com.alorma.tempcontacts.data.framework

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import com.alorma.tempcontacts.domain.model.Contact
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

class ContactDataSource @Inject constructor(private val context: Context) {

    operator fun invoke(contactUri: Uri): Maybe<Contact> {
        return Maybe.defer {
            getContact(contactUri)?.let {
                Maybe.just(it)
            } ?: Maybe.empty<Contact>()
        }
    }

    private fun getContact(contactUri: Uri): Contact? =
            context.contentResolver.query(contactUri, null, null, null, null)
                    .takeIf { it.moveToFirst() }?.let {
                        val idIndex = it.getColumnIndex(ContactsContract.Contacts._ID)
                        val nameIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

                        val id = it.getString(idIndex)
                        val name = it.getString(nameIndex)

                        it.close()

                        Contact(null, id, name)
                    }

    fun delete(it: Contact): Completable = Completable.complete().doOnComplete {
        val uri = ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(),
                it.androidId.toLong()).build()

        context.contentResolver.delete(uri, null, null)
    }
}