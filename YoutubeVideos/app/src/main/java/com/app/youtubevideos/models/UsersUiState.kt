package com.app.youtubevideos.models

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

data class UsersUiState(
    private val loadState: LoadState, val combinedLoadStates: CombinedLoadStates, val itemCount: Int
) : BaseUiState() {

    fun getProgressBarVisibility() = getViewVisibility(isVisible = loadState is LoadState.Loading)

//    fun getListVisibility() = getViewVisibility(isVisible = loadState is LoadState.NotLoading)

    fun getListVisibility() =getViewVisibility( loadState is LoadState.NotLoading && itemCount > 0)

    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is LoadState.Error)

    fun getEmptyView()= getViewVisibility(combinedLoadStates.source.refresh is LoadState.NotLoading && combinedLoadStates.append.endOfPaginationReached && itemCount == 0 || loadState is LoadState.Error )


    fun getErrorMessage() = if (loadState is LoadState.Error) {
        loadState.error.message ?: "Something went wrong"
    } else "Not Data Found"
}
