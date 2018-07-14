package com.alorma.tempcontacts.data.framework

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.extensions.queryExist
import com.alorma.tempcontacts.extensions.queryFirstLive
import javax.inject.Inject

class DocumentsDataSource @Inject constructor(private val context: Context,
                                              private val contactCursor: ContactCursor,
                                              private val imageCursor: ImageCursor,
                                              private val documentCursor: DocumentCursor) {

    fun loadDocument(documentUri: Uri): LiveData<AppDocument?> {
        val type = context.contentResolver.getType(documentUri)
        return context.queryFirstLive(documentUri) {
            when {
                type == "vnd.android.cursor.item/contact" -> contactCursor.loadContact(this, documentUri)
                type.startsWith("image") -> imageCursor.loadImage(this, documentUri)
                else -> documentCursor.loadDocument(this, documentUri)
            }
        }
    }

    fun delete(androidId: String) {
        /*
        val uri = ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(),
                androidId.toLong()).build()

        delete(uri)
        */
    }

    private fun delete(uri: Uri) {
        //context.contentResolver.delete(uri, null, null)
    }

    fun exist(androidId: String): Boolean {
        val uri = ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(),
                androidId.toLong()).build()

        return context.queryExist(uri)
    }
}