package com.alorma.tempcontacts.domain.model

data class AppDocument(val androidId: String, val name: String, val time: Long, val type: Type)

sealed class Type {
    object Contact : Type()
    object Image : Type()
    object Document : Type()
    object Unknown : Type()

    companion object {
        fun map(type: String): Type = when {
            type == "vnd.android.cursor.item/contact" -> Contact
            type.startsWith("image") -> Image
            else -> Document
        }
    }
}