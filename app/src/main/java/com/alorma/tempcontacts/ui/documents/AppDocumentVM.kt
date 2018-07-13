package com.alorma.tempcontacts.ui.documents

sealed class AppDocumentVM {
    data class SectionType(val name: String) : AppDocumentVM()
    data class Document(val name: String, val timeToDelete: Long): AppDocumentVM()
}