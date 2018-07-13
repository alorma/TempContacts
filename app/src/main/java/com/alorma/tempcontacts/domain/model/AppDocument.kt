package com.alorma.tempcontacts.domain.model

data class AppDocument(val androidId: String, val name: String, val time: Long, val type: Type)


sealed class Type {
    object Contact : Type()
    object Image : Type()
    object Document : Type()
    object Unknown : Type()
}