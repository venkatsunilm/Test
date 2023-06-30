package com.app.youtubevideos.utitlies

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import com.app.youtubevideos.R
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView


@BindingAdapter("loadimage")
fun loadimage(imageView: ShapeableImageView, image: String?) {
    image?.let {
        Glide.with(imageView.context)
            .load(image)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(imageView)
    }

}


