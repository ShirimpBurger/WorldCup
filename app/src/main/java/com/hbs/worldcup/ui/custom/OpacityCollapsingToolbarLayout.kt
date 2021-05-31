package com.hbs.worldcup.ui.custom

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class OpacityCollapsingToolbarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CollapsingToolbarLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        with(getChildAt(0)) {
            alpha = ((verticalOffset + height).toFloat() / height.toFloat())
        }
    }
}