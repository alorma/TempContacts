package com.alorma.tempcontacts.ui.contacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import kotlinx.android.synthetic.main.contacts_list_activity.*
import javax.inject.Inject

class ContactsListActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: ContactsNavigator

    @Inject
    lateinit var viewModel: ContactsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contacts_list_activity)

        component add ContactsListModule(this) inject this

        viewModel.subscribe().observe(this, Observer {
            it?.let { onState(it) }
        })
        viewModel.load()

        fab.setOnClickListener { navigator.openCreateContact() }
    }

    private fun onState(it: ContactsList.ContactsState) {

    }
}