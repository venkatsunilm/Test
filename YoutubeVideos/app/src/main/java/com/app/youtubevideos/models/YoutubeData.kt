package com.app.youtubevideos.models

import com.google.gson.annotations.SerializedName


data class PageInfo(
    @SerializedName("totalResults")
    val totalResults: String = "",
    @SerializedName("resultsPerPage")
    val resultsPerPage: String = ""
)


data class YoutubeData(
    @SerializedName("prevPageToken")
    val prevPageToken: String = "",
    @SerializedName("regionCode")
    val regionCode: String = "",
    @SerializedName("kind")
    val kind: String = "",
    @SerializedName("nextPageToken")
    val nextPageToken: String = "",
    @SerializedName("pageInfo")
    val pageInfo: PageInfo,
    @SerializedName("etag")
    val etag: String = "",
    @SerializedName("items")
    val items: List<ItemsItem>?
) {
    data class ItemsItem(
        @SerializedName("snippet")
        val snippet: Snippet,
        @SerializedName("kind")
        val kind: String = "",
        @SerializedName("etag")
        val etag: String = "",
        @SerializedName("id")
        val id: Id
    ) {

        data class Id(
            @SerializedName("playlistId")
            val playlistId: String = "",
            @SerializedName("kind")
            val kind: String = "",
            @SerializedName("videoId")
            val videoId: String = "",
            @SerializedName("channelId")
            val channelId: String = ""
        )

        data class Snippet(
            @SerializedName("publishedAt")
            val publishedAt: String = "",
            @SerializedName("description")
            val description: String = "",
            @SerializedName("title")
            val title: String = "",
            @SerializedName("thumbnails")
            val thumbnails: Thumbnails,
            @SerializedName("channelId")
            val channelId: String = "",
            @SerializedName("channelTitle")
            val channelTitle: String = "",
            @SerializedName("liveBroadcastContent")
            val liveBroadcastContent: String = ""
        ) {
            data class Thumbnails(
                @SerializedName("default")
                val key: Key
            ) {
                data class Key(
                    @SerializedName("width")
                    val width: String = "",
                    @SerializedName("url")
                    val url: String = "",
                    @SerializedName("height")
                    val height: String = ""
                )

            }
        }

    }

}

