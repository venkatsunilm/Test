package com.app.youtubevideos.data.repository


import com.app.youtubevideos.data.repository.BaseRepository
import com.app.youtubevideos.data.remote.ApiService
import com.app.youtubevideos.utitlies.createPager
import javax.inject.Inject

class NewsFeedRepo @Inject constructor(private val apiService: ApiService) : BaseRepository() {
    fun getVideos(query: String) = createPager {
        it["q"] = query
        it["part"] = "snippet"
        it["maxResults"] =25
        it["key"] ="AIzaSyCqUh4lbf1fZiYasMQhS6sDygPK6xeaMkk"

        apiService.getYoutubeData(it)
    }.flow



}

