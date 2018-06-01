package com.alorma.tempcontacts.ui.newcontact

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.dsl.dsl
import com.karumi.dexter.DexterBuilder
import kotlinx.android.synthetic.main.new_contact_activity.*
import javax.inject.Inject

class NewContactActivity : AppCompatActivity() {

    @Inject
    lateinit var permission: DexterBuilder.Permission


    @Inject
    lateinit var navigator: NewContactNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_contact_activity)

        component add NewContactModule(this) inject this

        fabCreate.setOnClickListener {
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

    private fun executePermission(onGrantedDo: () -> Unit) {
        permission.dsl(Manifest.permission.WRITE_CONTACTS) {
            onGranted {
                onGrantedDo()
            }
            onDenied {
                Toast.makeText(this@NewContactActivity, "Why? :(", Toast.LENGTH_SHORT).show()
            }
            rationale { _, accept, _ -> accept() }
        }.check()
    }

}