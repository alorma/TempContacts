package com.alorma.tempcontacts.ui.documents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.alorma.tempcontacts.R
import kotlinx.android.synthetic.main.fragment_documents.view.*

class DocumentsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_documents, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.fab.setOnClickListener {
            findNavController(it).navigate(R.id.action_documentsFragment_to_createDocumentFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): DocumentsFragment = DocumentsFragment()
    }
}
