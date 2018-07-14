package com.alorma.tempcontacts.ui.select

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.dsl.dsl
import com.karumi.dexter.DexterBuilder
import kotlinx.android.synthetic.main.fragment_select_document.view.*
import javax.inject.Inject

private const val REQ_CONTACT: Int = 1121
private const val REQ_FILE: Int = 1123

class SelectDocumentFragment : Fragment() {

    @Inject
    lateinit var permission: DexterBuilder.Permission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component add SelectDocumentModule(this) inject this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_select_document, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.contactCreate.setOnClickListener {
            getContactPermission {
                startActivityForResult(Intent(ContactsContract.Intents.Insert.ACTION).apply {
                    type = ContactsContract.Contacts.CONTENT_TYPE
                    putExtra("finishActivityOnSaveCompleted", true)
                }, REQ_CONTACT)
            }
        }
        view.contactImport.setOnClickListener {
            getContactPermission {
                startActivityForResult(Intent(Intent.ACTION_PICK).apply {
                    type = ContactsContract.Contacts.CONTENT_TYPE
                }, REQ_CONTACT)
            }
        }

        view.documentImport.setOnClickListener {
            getStoragePermission {
                startActivityForResult(Intent(Intent.ACTION_GET_CONTENT).apply {
                    type = "*/*"
                }, REQ_FILE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.data?.let {
            val directions = SelectDocumentFragmentDirections.configDocument(it.toString())
            findNavController(this).navigate(directions)
        }
    }

    private fun getContactPermission(function: () -> Unit) {
        permission.dsl(arrayOf(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS)) {
            onDenied {
                Toast.makeText(context, "AppDocument permission denied", Toast.LENGTH_SHORT).show()
            }

            rationale { _, accept, _ ->
                accept()
            }

            onGranted {
                function()
            }
        }.check()
    }

    private fun getStoragePermission(function: () -> Unit) {
        permission.dsl(
                arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
        ) {
            onGranted {
                function()
            }
            onDenied {
                Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
            rationale { _, accept, _ -> accept() }
        }.check()
    }
}
