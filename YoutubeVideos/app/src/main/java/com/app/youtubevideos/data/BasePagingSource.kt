package com.app.youtubevideos.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.youtubevideos.models.YoutubeData

class BasePagingSource(
    private val block: suspend (HashMap<String, Any>) -> YoutubeData
) : PagingSource<Int, Any>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        val page = params.key ?: 1

        return try {
            val hashMap = HashMap<String, Any>()
            hashMap["current_page"] = page

            val response = block(hashMap)
            LoadResult.Page(
                data = response.items as List<YoutubeData.ItemsItem>,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.items.isNullOrEmpty()) null else page+1
//                nextKey = page+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}




