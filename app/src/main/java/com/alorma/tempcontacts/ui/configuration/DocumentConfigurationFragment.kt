package com.alorma.tempcontacts.ui.configuration

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.extensions.observe
import kotlinx.android.synthetic.main.fragment_config_document.*
import javax.inject.Inject

class DocumentConfigurationFragment : Fragment() {

    @Inject
    lateinit var viewModel: ConfigDocumentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component add ConfigDocumentModule(this) inject this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_config_document, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.subscribe(this)

        arguments?.getString(EXTRA_URI)?.let {
            viewModel.onDocumentUriLoaded(Uri.parse(it)).observe(this) {
                it?.let {
                    contactName.text = it.name
                }
            }
        }
    }

    companion object {
        private const val EXTRA_URI = "uri"
        fun generateArguments(uri: String): Bundle = bundleOf(EXTRA_URI to uri)
    }

}