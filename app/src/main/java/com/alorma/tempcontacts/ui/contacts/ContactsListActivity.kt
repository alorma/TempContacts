package com.alorma.tempcontacts.ui.contacts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import javax.inject.Inject

class ContactsListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ContactsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component add ContactsListModule(this) inject this

        viewModel.subscribe().observe(this, Observer {
            it?.let { onState(it) }
        })
        viewModel.load()
    }

    private fun onState(it: ContactsList.State) {
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }
}