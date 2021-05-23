package com.hbs.worldcup.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseFragment
import com.hbs.worldcup.databinding.SettingFragmentBinding
import com.hbs.worldcup.models.FragmentInitializer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingFragmentBinding>() {
    private val viewModel : SettingViewModel by viewModels()
    private val settingAdapter = SettingAdapter {
        viewModel.insertAndSelectedAllItems(it)
    }.apply {
        setHasStableIds(true)
    }

    override fun getFragmentInitializer() = FragmentInitializer(R.layout.setting_fragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingAdapter()
        observeViewModel()
    }

    private fun bindingAdapter() {
        binding.settingRecyclerview.adapter = settingAdapter
    }

    private fun observeViewModel() {
        viewModel.settingItems.observe(viewLifecycleOwner, {
            settingAdapter.submitList(it)
        })
    }
}