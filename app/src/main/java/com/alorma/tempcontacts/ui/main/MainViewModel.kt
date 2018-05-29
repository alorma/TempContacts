package com.alorma.tempcontacts.ui.main

import android.Manifest
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alorma.tempcontacts.domain.model.Contact
import com.alorma.tempcontacts.domain.repository.ContactRepository
import com.alorma.tempcontacts.dsl.PermissionBuilder
import com.alorma.tempcontacts.dsl.dsl
import com.karumi.dexter.DexterBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val permission: DexterBuilder.Permission,
                    private val navigation: MainNavigation,
                    private val contactRepository: ContactRepository) : ViewModel() {

    private lateinit var contactUri: MutableLiveData<Contact>
    private lateinit var contacts: MutableLiveData<List<Contact>>

    private lateinit var permissionBuilder: PermissionBuilder

    fun loadContacts(): MutableLiveData<List<Contact>> {
        if (!::contacts.isInitialized) {
            contacts = MutableLiveData()
        }

        contactRepository()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    contacts.postValue(it)
                    Log.i("Alorma", it.toString())
                }, {

                })

        return contacts
    }

    fun setupPermission(onDeny: () -> Unit, rational: (String) -> Boolean = { true }) {
        permissionBuilder = permission.dsl(Manifest.permission.WRITE_CONTACTS) {
            onGranted {
                navigation.openContacts()
            }
            onDenied { onDeny() }
            rationale { permissionName, accept, cancel ->
                if (rational(permissionName)) {
                    accept()
                } else {
                    cancel()
                }
            }
        }
    }

    fun getNewContact(): LiveData<Contact> {
        contactUri = MutableLiveData()
        permissionBuilder.check()
        return contactUri
    }

    fun onResult(data: Intent?) {
        navigation.parse(data)?.let {
            contactRepository(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        contactUri.postValue(it)
                        loadContacts()
                    }, {

                    })
        }
    }
}
