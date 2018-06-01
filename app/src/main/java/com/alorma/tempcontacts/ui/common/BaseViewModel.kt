package com.alorma.tempcontacts.ui.common

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel<A : State> : ViewModel(), LifecycleObserver {

    private val liveData: MutableLiveData<A> = MutableLiveData()
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun subscribe(owner: LifecycleOwner, observer: Observer<in A>): LiveData<A> {
        owner.lifecycle.addObserver(this)
        liveData.observe(owner, observer)
        return liveData
    }

    protected fun render(a: A) = liveData.postValue(a)

    fun add(disposable: Disposable) {
        this.disposable += disposable
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}