package com.alorma.tempcontacts.ui.contacts

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.alorma.tempcontacts.ui.common.Navigator
import javax.inject.Inject

class ContactsNavigator @Inject constructor(
        private val activity: FragmentActivity) : Navigator(activity) {

    fun showCreateContact(import: () -> Unit, create: () -> Unit) {
        AlertDialog.Builder(activity)
                .setMessage("Select source")
                .setPositiveButton("Import") { p0, p1 -> import() }
                .setNegativeButton("Create") { p0, p1 -> create() }
                .create()
                .show()
    }
}