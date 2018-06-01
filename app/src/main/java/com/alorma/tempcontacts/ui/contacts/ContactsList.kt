package com.alorma.tempcontacts.ui.contacts

import javax.inject.Inject

class ContactsList @Inject constructor() {
    sealed class State {
        object Dummy : State()
    }

    fun dummy(): State = State.Dummy
}