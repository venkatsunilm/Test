package com.app.youtubevideos.utitlies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.app.youtubevideos.data.BasePagingSource
import com.app.youtubevideos.models.YoutubeData

private const val DEFAULT_PAGE_SIZE = 10

fun createPager(
    pageSize: Int = DEFAULT_PAGE_SIZE,
    enablePlaceholders: Boolean = false,
    block: suspend (HashMap<String,Any>) -> YoutubeData
): Pager<Int, Any> = Pager(
    config = PagingConfig(enablePlaceholders = enablePlaceholders, pageSize = pageSize),
    pagingSourceFactory = { BasePagingSource(block) }
)