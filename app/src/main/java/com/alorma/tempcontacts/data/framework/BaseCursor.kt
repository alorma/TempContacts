package com.alorma.tempcontacts.data.framework

import android.database.Cursor
import android.net.Uri
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type

interface BaseCursor {
    fun load(cursor: Cursor, documentId: String): AppDocument
    fun delete(documentUri: Uri) {}
    fun exist(documentUri: Uri, type: Type): Boolean
}