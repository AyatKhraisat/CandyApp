package com.ayat.candyapp.bases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 *Created by Ayat Khriasat on 28,June,2019 at 14:20
 *Email: ayatzkhraisat@gmail.com
 *Project: CandyApp
 **/
class BaseListAdapter <T >(@LayoutRes val layoutId: Int) :
RecyclerView.Adapter<BaseListAdapter.GenericViewHolder<T>>() {

    private val items = mutableListOf<T>()
    private var inflater: LayoutInflater? = null

    fun addItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false)
        return GenericViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        val model = items[position]
        holder.bind(model)
    }

    class GenericViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: T) {
            binding.setVariable(BR.model, model)
            binding.executePendingBindings()
        }

    }

}