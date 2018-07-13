package com.alorma.tempcontacts.data.framework

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import com.alorma.tempcontacts.extensions.queryExist
import com.alorma.tempcontacts.extensions.queryFirstLive
import javax.inject.Inject


class DocumentsDataSource @Inject constructor(private val context: Context) {

    fun loadDocument(documentUri: Uri): LiveData<AppDocument?> = context.queryFirstLive(documentUri) {
        val nameIndex = getColumnIndex("display_name").takeIf { it != -1 }
                ?: getColumnIndex("_display_name")

        val name = getString(nameIndex)

        val type = context.contentResolver.getType(documentUri)

        AppDocument(documentUri.toString(), name, 0, Type.map(type))
    }

    fun delete(androidId: String) {
        /*
        val uri = ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(),
                androidId.toLong()).build()

        delete(uri)
        */
    }

    private fun delete(uri: Uri) {
        context.contentResolver.delete(uri, null, null)
    }

    fun exist(androidId: String): Boolean {
        val uri = ContentUris.appendId(ContactsContract.Contacts.CONTENT_URI.buildUpon(),
                androidId.toLong()).build()

        return context.queryExist(uri)
    }
}