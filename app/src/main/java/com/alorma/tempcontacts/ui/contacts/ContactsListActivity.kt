package com.alorma.tempcontacts.ui.contacts

import android.os.Bundle
import android.widget.Toast
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

        fab.setOnClickListener {
            navigator.showCreateContact(
                    import = {
                        Toast.makeText(this@ContactsListActivity, "Import contact", Toast.LENGTH_SHORT).show()
                    }, create = {
                Toast.makeText(this@ContactsListActivity, "Create new contact", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun onState(it: ContactsList.ContactsState) {
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }
}