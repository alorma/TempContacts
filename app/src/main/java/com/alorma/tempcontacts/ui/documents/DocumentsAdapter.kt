package com.alorma.tempcontacts.ui.documents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alorma.tempcontacts.R
import kotlinx.android.synthetic.main.row_document_section.view.*

private const val TYPE_TITLE = 1
private const val TYPE_DOCUMENT = 2

class DocumentsAdapter :
        ListAdapter<AppDocumentVM, DocumentsAdapter.Holder<AppDocumentVM>>(AppDocumentVmDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<AppDocumentVM> =
            with(LayoutInflater.from(parent.context)) {
                when (viewType) {
                    TYPE_TITLE -> TitleViewHolder(inflate(R.layout.row_document_section, parent, false))
                    else -> DocumentHolder(inflate(android.R.layout.simple_list_item_1, parent, false))
                }
            } as Holder<AppDocumentVM>

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is AppDocumentVM.SectionType -> TYPE_TITLE
        is AppDocumentVM.Document -> TYPE_DOCUMENT
    }

    override fun onBindViewHolder(holder: Holder<AppDocumentVM>, position: Int) {
        holder.bind(getItem(position))
    }

    class TitleViewHolder(itemView: View) : Holder<AppDocumentVM.SectionType>(itemView) {
        override fun bind(t: AppDocumentVM.SectionType) {
            itemView.titleView.text = t.name
        }
    }

    class DocumentHolder(itemView: View) : Holder<AppDocumentVM.Document>(itemView) {
        override fun bind(t: AppDocumentVM.Document) {
            itemView.findViewById<TextView>(android.R.id.text1).text = t.name
        }
    }

    abstract class Holder<T : AppDocumentVM>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(t: T)
    }
}