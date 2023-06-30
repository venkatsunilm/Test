package com.app.youtubevideos.ui.adapters

import android.view.ViewGroup
import com.app.youtubevideos.R

import com.app.youtubevideos.databinding.ItemRecyclerVideosBinding

import com.app.youtubevideos.models.YoutubeData

class NewsFeedPagingAdapter(private var itemRecyclerListner: ItemRecyclerListner) : DiffUtilPagedListAdapter<YoutubeData.ItemsItem, ItemRecyclerVideosBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemRecyclerVideosBinding> {
        return BaseViewHolder(createBinding(R.layout.item_recycler_videos, parent))
    }

    override fun bind(binding: ItemRecyclerVideosBinding, item: YoutubeData.ItemsItem, position: Int) {
        binding.data=item
        binding.listner=itemRecyclerListner
        binding.executePendingBindings()
    }

}