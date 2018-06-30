package com.alorma.tempcontacts.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

inline fun <reified T, reified R> LiveData<T>.map(crossinline func: (T) -> R): LiveData<R> =
        Transformations.map(this) { func(it) }