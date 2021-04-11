package com.hbs.worldcup.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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
}