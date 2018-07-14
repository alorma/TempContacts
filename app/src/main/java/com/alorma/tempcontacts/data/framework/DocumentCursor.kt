package com.alorma.tempcontacts.data.framework

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import java.io.File

class DocumentCursor(private val context: Context) : BaseCursor {

    override fun load(cursor: Cursor, documentId: String): AppDocument {
        val idIndex = cursor.getColumnIndex("document_id")
        val nameIndex = cursor.getColumnIndex("_display_name")

        val id = cursor.getString(idIndex).replace("raw:", "")
        val documentFile = File(id).toUri()
        val name = cursor.getString(nameIndex)

        return AppDocument(documentId, name, documentFile, 0, Type.Document)
    }

    override fun exist(documentUri: Uri, type: Type): Boolean =
            documentUri.toFile().exists()

    override fun delete(documentUri: Uri, type: Type) {
        documentUri.toFile().delete()
    }

    companion object {
        const val NAME = "DOCUMENT"
    }
}