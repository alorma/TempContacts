package com.alorma.tempcontacts.ui.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.alorma.tempcontacts.R
import kotlinx.android.synthetic.main.fragment_config_document.*

class DocumentConfigurationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_config_document, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uriText.text = arguments?.getString(EXTRA_URI)
    }

    companion object {
        private const val EXTRA_URI = "uri"
        fun generateArguments(uri: String): Bundle = bundleOf(EXTRA_URI to uri)
    }

}