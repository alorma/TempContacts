package com.alorma.tempcontacts.ui.newcontact

import com.alorma.tempcontacts.ui.common.State
import javax.inject.Inject

class NewContact @Inject constructor() {
    sealed class NewState : State() {
        object InvalidTime : NewState()
        object Complete : NewState()
    }

    fun saveComplete(): NewState = NewState.Complete
    fun invalidTime(): NewState = NewState.InvalidTime
}