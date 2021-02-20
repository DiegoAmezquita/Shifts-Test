package com.diego.shifts.others

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("bindImage")
fun ImageView.bindImage(imageUrl: String?) {
  load(imageUrl)
}