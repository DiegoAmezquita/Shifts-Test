package com.diego.shifts.others

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy

@BindingAdapter("bindImage")
fun ImageView.bindImage(imageUrl: String?) {
  load(imageUrl) {
    memoryCachePolicy(CachePolicy.DISABLED)
  }
}

@BindingAdapter("android:visibility")
fun View.setVisibility(isVisible: Boolean) {
  visibility = if (isVisible) View.VISIBLE else View.GONE
}