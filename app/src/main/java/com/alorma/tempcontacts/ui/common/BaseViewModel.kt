package com.alorma.tempcontacts.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel<A : State> : ViewModel() {

    private val liveData: MutableLiveData<A> = MutableLiveData()
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun subscribe(): LiveData<A> = liveData

    protected fun render(a: A) = liveData.postValue(a)

    fun add(disposable: Disposable) {
        this.disposable += disposable
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}