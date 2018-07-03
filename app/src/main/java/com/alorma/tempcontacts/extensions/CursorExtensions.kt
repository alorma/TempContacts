package com.alorma.tempcontacts.extensions

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.LiveData
import com.alorma.tempcontacts.data.ActionLiveData

fun Context.queryExist(uri: Uri): Boolean {
    val cursor = query(uri, false)
    return cursor.moveToFirst().apply { cursor.close() }
}

fun Context.query(uri: Uri, close: Boolean = false): Cursor {
    return contentResolver.query(uri, null, null, null, null).apply {
        if (close) {
            close()
        }
    }
}

inline fun <reified T> Cursor.mapFirst(block: Cursor.() -> T): T? = if (moveToFirst()) {
    val result = block()
    close()
    result
} else {
    null
}

inline fun <reified T> Cursor.map(block: Cursor.() -> T): List<T> = if (moveToFirst()) {
    val list = mutableListOf<T>()
    do {
        list.add(block())
    } while (moveToNext())
    close()
    list
} else {
    emptyList()
}

inline fun <reified T> Context.queryFirstLive(uri: Uri, crossinline block: Cursor.() -> T)
        : LiveData<T?> = ActionLiveData {
    query(uri).mapFirst(block)
}

inline fun <reified T> Context.queryLive(uri: Uri, crossinline block: Cursor.() -> T)
        : LiveData<List<T>> = ActionLiveData {
    query(uri).map(block)
}