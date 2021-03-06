package com.alorma.tempcontacts.data.framework

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import java.io.File

class ImageCursor(private val context: Context) : BaseCursor {

    override fun load(cursor: Cursor, documentId: String): AppDocument {
        val nameIndex = cursor.getColumnIndex("_display_name")
        val dataIndex = cursor.getColumnIndex("_data")
        val documentIdIndex = cursor.getColumnIndex("document_id")

        val name = cursor.getString(nameIndex)

        val data = if (dataIndex != -1) {
            cursor.getString(dataIndex)
        } else {
            cursor.getString(documentIdIndex).replace("raw:", "")
        }

        val dataFile = File(data).toUri()
        return AppDocument(data, name, dataFile, 0, Type.Image)
    }

    override fun exist(documentUri: Uri, type: Type): Boolean =
            documentUri.toFile().exists()

    override fun delete(documentUri: Uri, type: Type) {
        documentUri.toFile().delete()
    }

    companion object {
        const val NAME = "IMAGE"
    }
}