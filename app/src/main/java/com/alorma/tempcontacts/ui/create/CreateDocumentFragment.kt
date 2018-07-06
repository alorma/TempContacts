package com.alorma.tempcontacts.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alorma.tempcontacts.R


class CreateDocumentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_create_document, container, false)


    companion object {
        @JvmStatic
        fun newInstance(): CreateDocumentFragment = CreateDocumentFragment()
    }
}
