package com.app.youtubevideos.models

import android.view.View

open class BaseUiState {
    fun getViewVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.GONE
}