package com.hbs.worldcup.core

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.hbs.domain.model.core.ActivityInitializer

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: B
    abstract fun getActivityInitializer(): ActivityInitializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindActivity()
    }

    private fun bindActivity() = getActivityInitializer()
        .takeIf { !it.isUseTransition }
        ?.let {
            binding = DataBindingUtil.setContentView(this, it.layoutId)
            binding.lifecycleOwner = this
        }

    fun motionToView(startView: View, endView: View) {
        val transform = MaterialContainerTransform().apply {
            this.startView = startView
            this.endView = endView

            addTarget(endView)

            this.pathMotion = MaterialArcMotion()
            this.scrimColor = Color.TRANSPARENT
        }

        TransitionManager.beginDelayedTransition(binding.root as ViewGroup, transform)
        endView.visibility = View.VISIBLE
    }
}