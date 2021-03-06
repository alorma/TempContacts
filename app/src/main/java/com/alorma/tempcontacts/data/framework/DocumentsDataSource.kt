package com.alorma.tempcontacts.data.framework

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import com.alorma.tempcontacts.extensions.queryFirstLive
import javax.inject.Inject


class DocumentsDataSource @Inject constructor(private val context: Context,
                                              private val cursorHandler: CursorHandler) {

    fun loadDocument(documentUri: Uri): LiveData<AppDocument?> {
        val documentId = documentUri.toString()
        return context.queryFirstLive(documentUri) {
            cursorHandler.load(this, documentId)
        }
    }

    fun delete(uri: Uri, type: Type) = cursorHandler.delete(uri, type)

    fun exist(documentUri: Uri, type: Type): Boolean = cursorHandler.exist(documentUri, type)
}