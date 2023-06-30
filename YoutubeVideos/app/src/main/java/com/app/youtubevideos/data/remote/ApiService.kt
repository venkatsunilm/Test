package com.app.youtubevideos.data.remote

import com.app.youtubevideos.models.YoutubeData
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("search")
    suspend fun getYoutubeData(@QueryMap params: HashMap<String, Any>): YoutubeData


}