package com.alorma.tempcontacts.ui.contacts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.dsl.DslAdapter
import com.alorma.tempcontacts.dsl.adapterDsl
import kotlinx.android.synthetic.main.contacts_list_activity.*
import javax.inject.Inject

class ContactsListActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: ContactsNavigator

    @Inject
    lateinit var viewModel: ContactsListViewModel

    private lateinit var adapter: DslAdapter<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contacts_list_activity)

        component add ContactsListModule(this) inject this

        viewModel.subscribe(this)

        viewModel.contacts.observe(this, Observer {
            it?.let { onState(it) }
        })

        adapter = adapterDsl(recycler) {
            item {
                layout = android.R.layout.simple_list_item_1
                bindView { view, contact ->
                    view.findViewById<TextView>(android.R.id.text1)?.text = contact.name
                }
            }
        }
        recycler.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { navigator.openCreateContact() }
    }

    private fun onState(it: ContactsList.ContactsState) {
        when (it) {
            is ContactsList.ContactsState.Items -> onItems(it)
        }
    }

    private fun onItems(it: ContactsList.ContactsState.Items) {
        adapter.submitList(it.items)
    }
}