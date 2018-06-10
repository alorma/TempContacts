package com.alorma.tempcontacts.dsl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@DslMarker
annotation class AdapterDsl

@AdapterDsl
class HolderBuilder<M> {

    @LayoutRes
    var layout: Int = 0
    internal lateinit var onBind: (View, M) -> Unit
    var onClick: ((M) -> Unit)? = null

    fun bindView(function: (View, M) -> Unit) {
        this.onBind = function
    }

    fun onClick(function: (M) -> Unit) {
        this.onClick = function
    }

    fun build(parent: ViewGroup): DslAdapter.ViewHolder<M> {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return DslAdapter.ViewHolder(inflater.inflate(layout, parent, false), this)
    }
}

@AdapterDsl
class AdapterDslBuilder<M> {

    private lateinit var holderBuilder: HolderBuilder<M>

    fun build(): DslAdapter<M> = DslAdapter(holderBuilder)

    fun item(setup: HolderBuilder<M>.() -> Unit) {
        this.holderBuilder = HolderBuilder<M>().apply(setup)
    }
}

class DslAdapter<M>(@LayoutRes private val holderBuilder: HolderBuilder<M>)
    : ListAdapter<M, DslAdapter.ViewHolder<M>>(ItemDiffAsync()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<M> =
            holderBuilder.build(parent)

    override fun onBindViewHolder(holder: ViewHolder<M>, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder<in M>(itemView: View,
                           private val holderBuilder: HolderBuilder<M>)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(vm: M) {
            with(holderBuilder) {
                onBind(itemView, vm)
                itemView.setOnClickListener {
                    onClick?.invoke(vm)
                }
            }
        }
    }
}


@AdapterDsl
fun <M> adapterDsl(recyclerView: RecyclerView,
                   setup: AdapterDslBuilder<M>.() -> Unit): DslAdapter<M> =
        with(AdapterDslBuilder<M>()) {
            setup()
            build().also { recyclerView.adapter = it }
        }