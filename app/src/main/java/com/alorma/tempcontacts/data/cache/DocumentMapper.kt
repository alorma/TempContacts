package com.alorma.tempcontacts.data.cache

import androidx.core.net.toUri
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.NewDocument
import com.alorma.tempcontacts.domain.model.Type
import javax.inject.Inject

class DocumentMapper @Inject constructor() {

    fun map(documentEntity: DocumentEntity): AppDocument =
            AppDocument(
                    documentEntity.id,
                    documentEntity.name,
                    documentEntity.uri.toUri(),
                    documentEntity.time,
                    mapDocument(documentEntity.type)
            )

    private fun mapDocument(type: String): Type = when (type) {
        Type.Document::class.java.simpleName -> Type.Document
        Type.Image::class.java.simpleName -> Type.Image
        Type.Contact::class.java.simpleName -> Type.Contact
        else -> Type.Unknown
    }

    fun map(newDocument: NewDocument): DocumentEntity =
            DocumentEntity(
                    newDocument.androidId,
                    newDocument.name,
                    newDocument.uri.toString(),
                    newDocument.type::class.java.simpleName,
                    newDocument.time
            )
}