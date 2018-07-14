package com.alorma.tempcontacts.data.framework

import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import java.io.File
import javax.inject.Inject

class DocumentCursor @Inject constructor() {

    fun loadDocument(cursor: Cursor, documentUri: Uri): AppDocument =
            cursor.mapCursorToDocument(documentUri)

    private fun Cursor.mapCursorToDocument(documentUri: Uri): AppDocument {
        val idIndex = getColumnIndex("document_id")
        val nameIndex = getColumnIndex("_display_name")

        val documentId = getString(idIndex).replace("raw:", "")
        val documentFile = File(documentId).toUri()
        val name = getString(nameIndex)

        return AppDocument(documentUri.toString(), name, documentFile, 0, Type.Document)
    }
}