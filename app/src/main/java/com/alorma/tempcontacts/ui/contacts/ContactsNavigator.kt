package com.alorma.tempcontacts.ui.contacts

import android.content.Intent
import android.provider.ContactsContract
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.alorma.tempcontacts.ui.common.Navigator
import javax.inject.Inject

class ContactsNavigator @Inject constructor(
        private val activity: FragmentActivity) : Navigator(activity) {

    companion object {
        const val REQ_CONTACT = 112
    }

    fun showCreateContact(import: () -> Unit, create: () -> Unit) {
        AlertDialog.Builder(activity)
                .setMessage("Select source")
                .setPositiveButton("Import") { p0, p1 -> import() }
                .setNegativeButton("Create") { p0, p1 -> create() }
                .create()
                .show()
    }

    fun openContacts() = startForResult(Intent(Intent.ACTION_PICK).apply {
        type = ContactsContract.Contacts.CONTENT_TYPE
    }, REQ_CONTACT)
}