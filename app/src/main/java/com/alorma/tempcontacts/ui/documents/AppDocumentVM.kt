package com.alorma.tempcontacts.ui.documents

import android.net.Uri

sealed class AppDocumentVM {
    data class SectionType(val name: String) : AppDocumentVM()
    sealed class Item : AppDocumentVM() {
        data class Contact(val name: String, val timeToDelete: String) : Item()
        data class Image(val name: String, val uri: Uri, val timeToDelete: String) : Item()
        data class Document(val name: String, val timeToDelete: String) : Item()
        object Invalid : Item()
    }
}