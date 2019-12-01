package com.pradip.shaadi.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter(value = ["app:imageurl", "app:placeholder"], requireAll = false)
fun loadImage(view: ImageView, image: String?, placeholder: Drawable?) {
    val imageUrl = image ?: ""
    Glide.with(view.context)
        .load(imageUrl)
        .also { glide ->
            placeholder?.also { drawable ->
                val requestOptions = RequestOptions().placeholder(drawable)
                glide.apply(requestOptions)
            }
        }.into(view)
}

