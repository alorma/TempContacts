package com.alorma.tempcontacts.ui.contacts

import com.alorma.tempcontacts.di.DataModule
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.ui.common.BaseViewModel
import io.reactivex.Scheduler
import javax.inject.Named

class ContactsListViewModel(private val operations: ContactsList,
                            private val contactRepository: ContactRepository,
                            @Named(DataModule.IO) private val io: Scheduler,
                            @Named(DataModule.IO) private val main: Scheduler) :
        BaseViewModel<ContactsList.ContactsState>() {

    fun load() {
        val disposable = contactRepository()
                .subscribeOn(io)
                .observeOn(main)
                .doOnSubscribe { render(operations.loading()) }
                .subscribe({
                    render(operations.items(it))
                }, {

                })
        add(disposable)
    }
}
