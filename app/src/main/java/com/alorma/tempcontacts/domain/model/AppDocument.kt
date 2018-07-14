package com.alorma.tempcontacts.domain.model

import android.net.Uri

data class AppDocument(val androidId: String, val name: String,
                       val uri: Uri, val time: Long, val type: Type)

sealed class Type {
    object Contact : Type()
    object Image : Type()
    object Document : Type()
    object Unknown : Type()
}