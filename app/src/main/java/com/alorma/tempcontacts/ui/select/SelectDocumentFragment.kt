package com.alorma.tempcontacts.ui.select

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.createNavigateOnClickListener
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.ui.configuration.DocumentConfigurationFragment
import kotlinx.android.synthetic.main.fragment_select_document.view.*

class SelectDocumentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_select_document, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        data?.data?.let {
            val bundle = DocumentConfigurationFragment.generateArguments(it.toString())
            findNavController(this).navigate(R.id.documentConfigurationFragment, bundle)
        }
    }

    companion object {
        private const val REQ_CONTACT: Int = 1121
    }
}
