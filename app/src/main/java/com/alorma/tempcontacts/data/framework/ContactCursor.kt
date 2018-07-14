package com.alorma.tempcontacts.data.framework

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import com.alorma.tempcontacts.extensions.queryExist

class ContactCursor(private val context: Context): BaseCursor {

    override fun load(cursor: Cursor, documentId: String): AppDocument {
        val nameIndex = cursor.getColumnIndex("display_name")
        val lookupIndex = cursor.getColumnIndex("lookup")

        val name = cursor.getString(nameIndex)
        val lookup = cursor.getString(lookupIndex)

        val contactUri = ContactsContract.Contacts.CONTENT_LOOKUP_URI
                .buildUpon()
                .appendPath(lookup)
                .build()

        return AppDocument(documentId, name, contactUri, 0, Type.Contact)
    }

    override fun exist(documentUri: Uri): Boolean = context.queryExist(documentUri)

    companion object {
        const val NAME = "CONTACT"
    }
}