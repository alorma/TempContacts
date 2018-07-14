package com.alorma.tempcontacts.data.framework

import android.database.Cursor
import android.net.Uri
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import javax.inject.Inject

class ContactCursor @Inject constructor() {

    fun loadContact(cursor: Cursor, documentUri: Uri): AppDocument =
            cursor.mapCursorToContact(documentUri)

    private fun Cursor.mapCursorToContact(documentUri: Uri): AppDocument {
        val nameIndex = getColumnIndex("display_name")

        val name = getString(nameIndex)

        return AppDocument(documentUri.toString(), name, documentUri, 0, Type.Contact)
    }
}