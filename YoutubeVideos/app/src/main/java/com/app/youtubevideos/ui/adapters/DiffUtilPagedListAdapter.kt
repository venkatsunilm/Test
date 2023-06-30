package com.app.youtubevideos.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import com.app.youtubevideos.models.YoutubeData


private class DiffUtilItemCallbackBuilder private constructor() {
    companion object {
        fun build(): DiffUtil.ItemCallback<YoutubeData.ItemsItem> {
            return object : DiffUtil.ItemCallback<YoutubeData.ItemsItem>() {

                override fun areItemsTheSame(
                    oldItem: YoutubeData.ItemsItem,
                    newItem: YoutubeData.ItemsItem
                ): Boolean {
                    return oldItem.id==newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: YoutubeData.ItemsItem,
                    newItem: YoutubeData.ItemsItem
                ): Boolean {
                   return oldItem==newItem
                }
            }
        }
    }

}

abstract class DiffUtilPagedListAdapter<T : YoutubeData.ItemsItem, VH : ViewDataBinding> :
    PagingDataAdapter<YoutubeData.ItemsItem, BaseViewHolder<VH>>(DiffUtilItemCallbackBuilder.build()) {

    abstract fun bind(binding: VH, item: T, position: Int)

    protected fun <B : ViewDataBinding> createBinding(layoutRes: Int, parent: ViewGroup): B {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VH>, position: Int) {
        getItem(position)?.let {
            bind(holder.binder,it as T,position)
            holder.binder.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}