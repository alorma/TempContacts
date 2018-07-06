package com.alorma.tempcontacts.ui.documents

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.dsl.DslAdapter
import com.alorma.tempcontacts.dsl.adapterDsl
import com.alorma.tempcontacts.dsl.dsl
import com.karumi.dexter.DexterBuilder
import kotlinx.android.synthetic.main.fragment_documents.view.*
import javax.inject.Inject

class DocumentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_documents, container, false)

    @Inject
    lateinit var viewModel: DocumentsListViewModel

    @Inject
    lateinit var permission: DexterBuilder.Permission

    private lateinit var adapter: DslAdapter<Contact>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component add DocumentsListModule(this) inject this

        viewModel.subscribe(this)

        viewModel.documentsMapper.observe(this, Observer {
            it?.let { onState(it) }
        })

        adapter = adapterDsl(view.recycler) {
            item {
                layout = android.R.layout.simple_list_item_1
                bindView { view, contact ->
                    view.findViewById<TextView>(android.R.id.text1)?.text = contact.name
                }
            }
        }
        view.recycler.layoutManager = LinearLayoutManager(context)

        view.fab.setOnClickListener {
            checkPermission {
                findNavController(it).navigate(R.id.action_documentsFragment_to_createDocumentFragment)
            }
        }
    }

    private fun onState(it: DocumentsListMapper.ContactsState) {
        when (it) {
            is DocumentsListMapper.ContactsState.Items -> onItems(it)
        }
    }

    private fun onItems(it: DocumentsListMapper.ContactsState.Items) {
        adapter.submitList(it.items)
    }


    private fun checkPermission(function: () -> Unit) {
        val permissions = arrayOf(
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_CONTACTS
        )

        permission.dsl(permissions) {
            onDenied {
                Toast.makeText(context, it.joinToString { it }, Toast.LENGTH_SHORT).show()
            }

            rationale { _, accept, _ ->
                accept()
            }

            onGranted {
                function()
            }
        }.check()
    }
}
