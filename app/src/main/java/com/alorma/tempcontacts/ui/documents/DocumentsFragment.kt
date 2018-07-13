package com.alorma.tempcontacts.ui.documents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import kotlinx.android.synthetic.main.fragment_documents.view.*
import javax.inject.Inject

class DocumentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_documents, container, false)

    @Inject
    lateinit var viewModel: DocumentsListViewModel

    private lateinit var adapter: DocumentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component add DocumentsListModule(this) inject this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.subscribe(this)

        viewModel.documents.observe(this, Observer {
            it?.let { onState(it) }
        })

        adapter = DocumentsAdapter()
        view.recycler.adapter = adapter
        view.recycler.layoutManager = LinearLayoutManager(context)

        view.fab.setOnClickListener {
            findNavController(it).navigate(R.id.selectDocumentFragment)
        }
    }

    private fun onState(it: DocumentsListMapper.DocumentsState) {
        when (it) {
            is DocumentsListMapper.DocumentsState.Items -> onItems(it)
        }
    }

    private fun onItems(it: DocumentsListMapper.DocumentsState.Items) {
        adapter.submitList(it.items)
    }

}
