package com.alorma.tempcontacts.ui.newcontact

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.dsl.dsl
import com.karumi.dexter.DexterBuilder
import kotlinx.android.synthetic.main.new_contact_activity.*
import javax.inject.Inject

class NewContactActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: NewContactViewModel

    @Inject
    lateinit var permission: DexterBuilder.Permission

    @Inject
    lateinit var navigator: NewContactNavigator

    private var uri: Uri? = null

    private var createContact: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_contact_activity)

        component add NewContactModule(this) inject this

        fabCreate.setOnClickListener {
            this.createContact = true
            executePermission {
                navigator.createContact()
            }
        }

        fabImport.setOnClickListener {
            executePermission {
                navigator.importContact()
            }
        }
    }

    private fun showImportContact(contact: Contact) {
        contactInfo.visibility = View.VISIBLE
        contactName.text = contact.name

        saveButton.setOnClickListener {
            val time = when (timeSelector.checkedChipId) {
                R.id.time1Hour -> TimeSelection.HOUR
                R.id.time1Day -> TimeSelection.DAY
                R.id.time1Week -> TimeSelection.WEEK
                R.id.time1Month -> TimeSelection.MONTH
                R.id.timeOther -> TimeSelection.OTHER
                else -> TimeSelection.NONE
            }

            viewModel.save(contact, time).observe(this, Observer {
                it?.let {
                    when (it) {
                        NewContact.NewState.InvalidTime -> Toast.makeText(this@NewContactActivity, "No time selected", Toast.LENGTH_SHORT).show()
                        NewContact.NewState.Complete -> finish()
                    }
                }
            })
        }
    }

    private fun executePermission(onGrantedDo: () -> Unit) {
        val permissionsList = listOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
        ).toTypedArray()

        permission.dsl(permissionsList) {
            onGranted {
                onGrantedDo()
            }
            onDenied {
                Toast.makeText(this@NewContactActivity, "Why? :(", Toast.LENGTH_SHORT).show()
            }
            rationale { _, accept, _ -> accept() }
        }.check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        navigator.parse(data)?.let {
            buttons.visibility = View.GONE
            viewModel.onContact(it).observe(this, Observer {
                it?.let { showImportContact(it) }
            })
            if (createContact) this.uri = it
        }
    }
}