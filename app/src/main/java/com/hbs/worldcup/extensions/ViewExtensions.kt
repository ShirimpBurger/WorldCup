package com.hbs.worldcup.extensions

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter(value = ["imageResourceId"])
fun ShapeableImageView.setImageResourceId(resourceId:Int) {
    setImageDrawable(ContextCompat.getDrawable(context, resourceId))
}