package com.alorma.tempcontacts.ui.configuration

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.alorma.tempcontacts.R
import com.alorma.tempcontacts.TempContactsApp.Companion.component
import com.alorma.tempcontacts.domain.model.AppDocument
import com.alorma.tempcontacts.domain.model.Type
import com.alorma.tempcontacts.extensions.observe
import kotlinx.android.synthetic.main.fragment_config_document.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DocumentConfigurationFragment : Fragment() {

    private lateinit var appDocument: AppDocument

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

        val uri = DocumentConfigurationFragmentArgs.fromBundle(arguments).uri

        viewModel.onDocumentUriLoaded(Uri.parse(uri)).observe(this) {
            it?.let {
                appDocument = it
                contactName.text = it.name
                imageType.setImageResource(when (it.type) {
                    Type.Contact -> R.drawable.ic_person_add
                    Type.Image -> R.drawable.ic_camera
                    else -> R.drawable.ic_attach_file
                })
            }
        }

        saveButton.setOnClickListener {
            viewModel.save(appDocument, TimeSelection.Custom(3, TimeUnit.MINUTES))
                    .observe(this) {
                        when (it) {
                            ConfigDocumentMapper.NewState.InvalidTime -> {
                            }
                            ConfigDocumentMapper.NewState.Complete -> {
                                findNavController(this).popBackStack()
                            }
                        }
                    }
        }
    }
}