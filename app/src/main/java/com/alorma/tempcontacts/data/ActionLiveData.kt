package com.alorma.tempcontacts.data

import androidx.lifecycle.LiveData

class ActionLiveData<T>(private val func: () -> T) : LiveData<T>() {

    override fun onActive() {
        Thread {
            if (hasActiveObservers()) {
                postValue(func())
            }
        }.start()
    }
}