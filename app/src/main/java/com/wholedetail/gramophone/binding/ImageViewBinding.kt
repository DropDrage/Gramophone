package com.wholedetail.gramophone.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcUrl")
fun ImageView.loadFromUrl(url: String) {
    Glide.with(this).load(url).into(this)
}