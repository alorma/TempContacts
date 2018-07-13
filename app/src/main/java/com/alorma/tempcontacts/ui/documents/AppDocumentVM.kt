package com.alorma.tempcontacts.ui.documents

sealed class AppDocumentVM {
    data class SectionType(val name: String) : AppDocumentVM()
    sealed class Item : AppDocumentVM() {
        data class Contact(val name: String, val timeToDelete: Long) : Item()
        data class Image(val name: String, val timeToDelete: Long) : Item()
        data class Document(val name: String, val timeToDelete: Long) : Item()
        object Invalid : Item()
    }
}