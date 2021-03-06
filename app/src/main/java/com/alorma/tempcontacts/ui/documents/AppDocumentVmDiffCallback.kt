package com.alorma.tempcontacts.ui.documents

import androidx.recyclerview.widget.DiffUtil

class AppDocumentVmDiffCallback : DiffUtil.ItemCallback<AppDocumentVM>() {
    override fun areItemsTheSame(oldItem: AppDocumentVM, newItem: AppDocumentVM): Boolean =
            oldItem === newItem

    override fun areContentsTheSame(oldItem: AppDocumentVM, newItem: AppDocumentVM): Boolean =
            oldItem::class == newItem::class

}