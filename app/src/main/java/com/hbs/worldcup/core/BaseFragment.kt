package com.hbs.worldcup.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.hbs.worldcup.models.FragmentInitializer

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    lateinit var binding: B
    abstract fun getFragmentInitializer(): FragmentInitializer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindFragment(container)
    }

    private fun bindFragment(container: ViewGroup?) = getFragmentInitializer()
        .let {
            binding = DataBindingUtil.inflate(layoutInflater, it.layoutId, container, false)
            binding.lifecycleOwner = this
            binding.root
        }
}