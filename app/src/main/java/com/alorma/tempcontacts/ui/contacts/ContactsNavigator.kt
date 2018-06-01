package com.alorma.tempcontacts.ui.contacts

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.alorma.tempcontacts.ui.common.Navigator
import com.alorma.tempcontacts.ui.newcontact.NewContactActivity
import javax.inject.Inject

class ContactsNavigator @Inject constructor(
        private val activity: FragmentActivity) : Navigator(activity) {

    companion object {
        const val REQ_CONTACT = 112
    }

    fun openCreateContact() = startForResult(Intent(activity, NewContactActivity::class.java),
            REQ_CONTACT)
}