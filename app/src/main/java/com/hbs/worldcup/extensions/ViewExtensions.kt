package com.hbs.worldcup.extensions

import android.content.res.Configuration
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.hbs.worldcup.R
import com.hbs.worldcup.models.OneLineWithTaskItem

@BindingAdapter(value = ["imageResourceId"])
fun ShapeableImageView.setImageResourceId(resourceId:Int) {
    setImageDrawable(ContextCompat.getDrawable(context, resourceId))
}

@BindingAdapter(value = ["toggleResourceItem"])
fun ShapeableImageView.setToggleResourceId(item:OneLineWithTaskItem) {
    val uiMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    val color = if(item.isUse) {
        if(uiMode == Configuration.UI_MODE_NIGHT_YES) {
            ContextCompat.getColor(context, R.color.md_yellow_300)
        } else {
            ContextCompat.getColor(context, R.color.md_yellow_700)
        }
    } else {
        if(uiMode == Configuration.UI_MODE_NIGHT_YES) {
            ContextCompat.getColor(context, R.color.md_gray_300)
        } else {
            ContextCompat.getColor(context, R.color.md_gray_700)
        }
    }

    val toggleImageDrawable = ContextCompat.getDrawable(context, item.icon)?.apply {
        setTint(color)
    } ?: return

    setImageDrawable(toggleImageDrawable)
}