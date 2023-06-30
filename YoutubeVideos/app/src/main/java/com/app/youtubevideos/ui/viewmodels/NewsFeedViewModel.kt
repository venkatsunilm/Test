package com.app.youtubevideos.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.youtubevideos.models.YoutubeData
import com.app.youtubevideos.ui.adapters.ItemRecyclerListner
import com.app.youtubevideos.data.repository.NewsFeedRepo
import com.app.youtubevideos.utitlies.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(private val newsFeedRepo: NewsFeedRepo) :
    ViewModel() , ItemRecyclerListner {

    val onItemClicked = MutableLiveData<SingleEvent<YoutubeData.ItemsItem, String>>()

    private val _showProgressBar = MutableLiveData(true)
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    fun getVideos(query: String): Flow<PagingData<Any>> {
        return newsFeedRepo.getVideos(query).cachedIn(viewModelScope)
    }



    override fun <T, D> onItemClicked(movie: T, type: D) {
        onItemClicked.postValue(SingleEvent(movie as YoutubeData.ItemsItem, type as String))

    }


}