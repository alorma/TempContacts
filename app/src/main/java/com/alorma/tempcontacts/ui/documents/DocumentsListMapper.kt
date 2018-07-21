package com.alorma.tempcontacts.ui.documents

import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import com.alorma.tempcontacts.ui.common.State
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DocumentsListMapper @Inject constructor() {
    sealed class DocumentsState : State() {
        object Loading : DocumentsState()
        object Empty : DocumentsState()
        data class Items(val items: List<AppDocumentVM>) : DocumentsState()
    }

    private val shortFormatter: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()) }
    private val longFormatter: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("dd, HH:mm", Locale.getDefault()) }

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
        Type.Contact -> AppDocumentVM.Item.Contact(it.name, formatDeleteTime(it))
        Type.Image -> AppDocumentVM.Item.Image(it.name, it.uri, formatDeleteTime(it))
        Type.Document -> AppDocumentVM.Item.Document(it.name, formatDeleteTime(it))
        Type.Unknown -> AppDocumentVM.Item.Invalid
    }

    private fun formatDeleteTime(it: AppDocument): String {
        val instant = ZonedDateTime.ofInstant(Instant.ofEpochMilli(it.time), ZoneId.systemDefault())
        val time = it.time - System.currentTimeMillis()
        return if (time > TimeUnit.DAYS.toMillis(1)) longFormatter.format(instant)
        else shortFormatter.format(instant)
    }

    fun items(it: List<AppDocumentVM>): DocumentsState = if (it.isEmpty()) {
        DocumentsState.Empty
    } else {
        DocumentsState.Items(it)
    }
}