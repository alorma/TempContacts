package com.alorma.tempcontacts.domain.model

import android.net.Uri

data class NewDocument(
        val androidId: String,
        val name: String,
        val uri: Uri,
        val type: Type,
        val time: Long
)