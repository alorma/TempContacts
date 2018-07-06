package com.alorma.tempcontacts.ui.create

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import kotlinx.android.synthetic.main.fragment_create_document.view.*
import javax.inject.Inject

class CreateDocumentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_create_document, container, false)

    @Inject
    lateinit var viewModel: CreateDocumentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component add CreateDocumentModule(this) inject this

        view.fabCreate.setOnClickListener {
            startActivityForResult(Intent(ContactsContract.Intents.Insert.ACTION).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
                putExtra("finishActivityOnSaveCompleted", true)
            }, REQ_CONTACT)
        }
        view.fabImport.setOnClickListener {
            startActivityForResult(Intent(Intent.ACTION_PICK).apply {
                type = ContactsContract.Contacts.CONTENT_TYPE
            }, REQ_CONTACT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val REQ_CONTACT: Int = 1121
    }
}
