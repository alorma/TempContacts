package com.alorma.tempcontacts.ui.documents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alorma.tempcontacts.R
import kotlinx.android.synthetic.main.row_document_image.view.*
import kotlinx.android.synthetic.main.row_document_section.view.*

private const val TYPE_INVALID = -1
private const val TYPE_TITLE = 1
private const val TYPE_CONTACT = 2
private const val TYPE_IMAGE = 3
private const val TYPE_DOCUMENT = 4

class DocumentsAdapter :
        ListAdapter<AppDocumentVM, DocumentsAdapter.Holder<AppDocumentVM>>(AppDocumentVmDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<AppDocumentVM> =
            LayoutInflater.from(parent.context).let {
                when (viewType) {
                    TYPE_TITLE -> TitleViewHolder(it.inflate(R.layout.row_document_section, parent, false))
                    TYPE_CONTACT -> ContactHolder(it.inflate(android.R.layout.simple_list_item_1, parent, false))
                    TYPE_IMAGE -> ImageHolder(it.inflate(R.layout.row_document_image, parent, false))
                    TYPE_DOCUMENT -> DocumentHolder(it.inflate(android.R.layout.simple_list_item_1, parent, false))
                    else -> InvalidHolder(it.inflate(android.R.layout.simple_list_item_1, parent, false))
                } as Holder<AppDocumentVM>
            }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is AppDocumentVM.SectionType -> TYPE_TITLE
        is AppDocumentVM.Item.Contact -> TYPE_CONTACT
        is AppDocumentVM.Item.Image -> TYPE_IMAGE
        is AppDocumentVM.Item.Document -> TYPE_DOCUMENT
        else -> TYPE_INVALID
    }

    override fun onBindViewHolder(holder: Holder<AppDocumentVM>, position: Int) {
        holder.bind(getItem(position))
    }

    class TitleViewHolder(itemView: View) : Holder<AppDocumentVM.SectionType>(itemView) {
        override fun bind(t: AppDocumentVM.SectionType) {
            itemView.titleView.text = t.name
        }
    }

    class ContactHolder(itemView: View) : Holder<AppDocumentVM.Item.Contact>(itemView) {
        override fun bind(t: AppDocumentVM.Item.Contact) {
            itemView.findViewById<TextView>(android.R.id.text1).text = t.name
        }
    }

    class ImageHolder(itemView: View) : Holder<AppDocumentVM.Item.Image>(itemView) {
        override fun bind(t: AppDocumentVM.Item.Image) {
            itemView.imageView.setImageURI(t.uri)
        }
    }

    class DocumentHolder(itemView: View) : Holder<AppDocumentVM.Item.Document>(itemView) {
        override fun bind(t: AppDocumentVM.Item.Document) {
            itemView.findViewById<TextView>(android.R.id.text1).text = t.name
        }
    }

    class InvalidHolder(itemView: View) : Holder<AppDocumentVM.Item.Invalid>(itemView) {
        override fun bind(t: AppDocumentVM.Item.Invalid) {

        }
    }

    abstract class Holder<T : AppDocumentVM>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(t: T)
    }
}