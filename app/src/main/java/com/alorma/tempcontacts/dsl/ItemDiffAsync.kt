package com.alorma.tempcontacts.dsl

import androidx.recyclerview.widget.DiffUtil

class ItemDiffAsync<M> : DiffUtil.ItemCallback<M>() {
    override fun areItemsTheSame(oldItem: M, newItem: M): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: M, newItem: M): Boolean =
            oldItem?.equals(newItem) ?: false

}