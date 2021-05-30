package com.hbs.worldcup.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseFragment
import com.hbs.worldcup.databinding.SettingFragmentBinding
import com.hbs.worldcup.models.FragmentInitializer
import com.hbs.worldcup.models.OneLineWithTaskItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingFragmentBinding>() {
    private val viewModel: SettingViewModel by viewModels()
    private val settingAdapter = SettingAdapter {
        showSnackBar(it)
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

    private fun showSnackBar(item: OneLineWithTaskItem) {
        val message = if (item.isUse) {
            "ON ".plus(item.task)
        } else {
            "OFF ".plus(item.task)
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).setAction("Cancel") {
            it.postDelayed({
                viewModel.insertAndSelectedAllItems(item.copy(isUse = !item.isUse))
            }, 200L)
        }.show()
    }
}