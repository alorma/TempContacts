package com.alorma.tempcontacts.data.framework

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.extensions.queryExist
import javax.inject.Inject
import javax.inject.Named

class CursorHandler @Inject constructor(private val context: Context,
                                        @Named(ContactCursor.NAME) private val contactCursor: BaseCursor,
                                        @Named(ImageCursor.NAME) private val imageCursor: BaseCursor,
                                        @Named(DocumentCursor.NAME) private val documentCursor: BaseCursor)
    : BaseCursor {

    override fun load(cursor: Cursor, documentId: String): AppDocument {
        val documentUri = documentId.toUri()
        val type = context.contentResolver.getType(documentUri)
        return when {
            type == "vnd.android.cursor.item/contact" -> contactCursor.load(cursor, documentId)
            type.startsWith("image") -> imageCursor.load(cursor, documentId)
            else -> documentCursor.load(cursor, documentId)
        }
    }

    override fun delete(documentUri: Uri) {
        val type = context.contentResolver.getType(documentUri)
        when {
            type == "vnd.android.cursor.item/contact" -> contactCursor.delete(documentUri)
            type.startsWith("image") -> imageCursor.delete(documentUri)
            else -> documentCursor.delete(documentUri)
        }
    }

    override fun exist(documentUri: Uri): Boolean = context.queryExist(documentUri)
}