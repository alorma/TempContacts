package com.alorma.contactnotes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alorma.contactnotes.ContactNotesApp.Companion.component
import com.alorma.contactnotes.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            component add MainModule(it) inject this

            viewModel.setupPermission({
                Toast.makeText(context, "Granted", Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(context, "Deny", Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.fab.setOnClickListener {
            viewModel.load()
        }

    }

}
