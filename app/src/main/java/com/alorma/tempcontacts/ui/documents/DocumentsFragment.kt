package com.alorma.tempcontacts.ui.documents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.dsl.DslAdapter
import com.alorma.tempcontacts.dsl.adapterDsl
import kotlinx.android.synthetic.main.fragment_documents.view.*
import javax.inject.Inject

class DocumentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_documents, container, false)

    @Inject
    lateinit var viewModel: DocumentsListViewModel

    private lateinit var adapter: DslAdapter<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component add DocumentsListModule(this) inject this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            findNavController(it).navigate(R.id.action_documentsFragment_to_selectDocumentFragment)
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

}
