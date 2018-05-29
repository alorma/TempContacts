package com.alorma.tempcontacts.ui.main

import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import com.alorma.tempcontacts.ui.common.Navigator
import javax.inject.Inject

class MainNavigation @Inject constructor(private val fragment: Fragment) :
        Navigator(fragment) {

    companion object {
        const val REQ_CONTACT = 112
    }

    fun openContacts() = startForResult(Intent(Intent.ACTION_PICK).apply {
        type = ContactsContract.Contacts.CONTENT_TYPE
    }, REQ_CONTACT)

    fun parse(data: Intent?): Uri? = data?.data
}