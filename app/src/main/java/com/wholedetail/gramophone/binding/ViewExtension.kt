package com.wholedetail.gramophone.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.setBooleanVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}