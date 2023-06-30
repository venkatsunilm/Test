package com.app.youtubevideos.ui.adapters

interface ItemRecyclerListner {

    fun <T,D> onItemClicked(movie: T,type:D)
}
