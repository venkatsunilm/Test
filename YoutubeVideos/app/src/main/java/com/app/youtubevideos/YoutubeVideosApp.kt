package com.app.youtubevideos

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YoutubeVideosApp :Application() {
    override fun onCreate() {
        super.onCreate()
    }
}