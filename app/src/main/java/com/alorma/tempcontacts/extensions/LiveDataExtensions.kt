package com.alorma.tempcontacts.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.alorma.tempcontacts.data.ActionLiveData

inline fun <reified T, R> LiveData<T>.map(
        crossinline block: (T) -> R
): LiveData<R> = Transformations.map(this) { block(it) }

inline fun <reified T, R> LiveData<T>.switchMap(
        crossinline block: (T) -> LiveData<R>
): LiveData<R> = Transformations.switchMap(this) {
    block(it)
}

inline fun <reified T, R> LiveData<T>.actionSwitchMap(
        crossinline block: (T) -> R
): LiveData<R> = switchMap { ActionLiveData { block(it) } }

inline fun <reified T> LiveData<T>.observe(owner: LifecycleOwner, crossinline block: (T) -> Unit) {
    this.observe(owner, Observer {
        it?.let {
            block(it)
        }
    })
}