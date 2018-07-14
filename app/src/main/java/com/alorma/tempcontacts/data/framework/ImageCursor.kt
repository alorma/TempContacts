package com.alorma.tempcontacts.data.framework

import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import java.io.File
import javax.inject.Inject

class ImageCursor @Inject constructor() {

    fun loadImage(cursor: Cursor, documentUri: Uri): AppDocument =
            cursor.mapCursorToImage(documentUri)

    private fun Cursor.mapCursorToImage(documentUri: Uri): AppDocument {
        val nameIndex = getColumnIndex("_display_name")
        val dataIndex = getColumnIndex("_data")

        val name = getString(nameIndex)
        val data = getString(dataIndex)
        val dataFile = File(data).toUri()

        return AppDocument(documentUri.toString(), name, dataFile, 0, Type.Image)
    }
}