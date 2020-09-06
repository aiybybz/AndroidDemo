package com.demon.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl1")
fun ImageView.imageUrl1(url: String?) {
    if (url != null) Glide.with(context).load(url).into(this)
}

@BindingAdapter("imageUrl2")
fun ImageView.imageUrl2(url: String) = Glide.with(this.context).load(url).into(this)
