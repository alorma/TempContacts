package com.alorma.tempcontacts.ui.documents

import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import com.alorma.tempcontacts.ui.common.State
import javax.inject.Inject

class DocumentsListMapper @Inject constructor() {
    sealed class DocumentsState : State() {
        object Loading : DocumentsState()
        object Empty : DocumentsState()
        data class Items(val items: List<AppDocumentVM>) : DocumentsState()
    }

    fun loading(): DocumentsState = DocumentsState.Loading

    fun mapDocuments(it: List<AppDocument>): List<AppDocumentVM> =
            it.groupBy { it.type }
                    .toList()
                    .flatMap {
                        val list = mutableListOf<AppDocumentVM>()
                        list.add(mapType(it.first))

                        list.addAll(
                                it.second
                                        .map { mapDocument(it) }
                                        .filterNot { it === AppDocumentVM.Item.Invalid }
                        )

                        list
                    }

    private fun mapType(it: Type): AppDocumentVM = AppDocumentVM.SectionType(when (it) {
        Type.Contact -> "Contacts"
        Type.Image -> "Images"
        Type.Document -> "Documents"
        Type.Unknown -> "Unknown"
    })

    private fun mapDocument(it: AppDocument): AppDocumentVM = when (it.type) {
        Type.Contact -> AppDocumentVM.Item.Contact(it.name, it.time)
        Type.Image -> AppDocumentVM.Item.Image(it.name, it.uri, it.time)
        Type.Document -> AppDocumentVM.Item.Document(it.name, it.time)
        Type.Unknown -> AppDocumentVM.Item.Invalid
    }

    fun items(it: List<AppDocumentVM>): DocumentsState = if (it.isEmpty()) {
        DocumentsState.Empty
    } else {
        DocumentsState.Items(it)
    }
}