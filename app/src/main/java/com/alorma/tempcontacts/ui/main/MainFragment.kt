package com.alorma.tempcontacts.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.domain.model.Contact
import kotlinx.android.synthetic.main.main_fragment.view.*
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            component add MainModule(it, this) inject this

            viewModel.setupPermission({
                Toast.makeText(context, "Deny", Toast.LENGTH_SHORT).show()
            })
        }

        viewModel.loadContacts().observe(this, Observer {

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.fab.setOnClickListener {
            viewModel.getNewContact().observe(this@MainFragment, Observer {
                it?.let {
                    onContactLoaded(it)
                }
            })
        }


    }

    private fun onContactLoaded(it: Contact) {
        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        viewModel.onResult(data)
    }
}
