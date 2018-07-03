package com.alorma.tempcontacts.ui.common

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(), LifecycleObserver {

    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun subscribe(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    fun add(disposable: Disposable) {
        this.disposable += disposable
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}