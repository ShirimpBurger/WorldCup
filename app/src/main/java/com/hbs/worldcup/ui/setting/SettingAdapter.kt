package com.hbs.worldcup.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hbs.worldcup.databinding.OneLineWithTitleCardItemBinding
import com.hbs.worldcup.models.OneLineWithTaskItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class SettingAdapter (private val callback: Callback): ListAdapter<OneLineWithTaskItem, SettingAdapter.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OneLineWithTitleCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            item = getItem(position)
            callback = this@SettingAdapter.callback
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    class ViewHolder(val binding: OneLineWithTitleCardItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}

private val diffUtil = object :
    DiffUtil.ItemCallback<OneLineWithTaskItem>() {
    override fun areItemsTheSame(oldItem: OneLineWithTaskItem, newItem: OneLineWithTaskItem): Boolean {
        return oldItem.isUse == newItem.isUse && oldItem.task == newItem.task
    }

    override fun areContentsTheSame(oldItem: OneLineWithTaskItem, newItem: OneLineWithTaskItem): Boolean {
        return oldItem == newItem
    }
}

fun interface Callback {
    fun onClick(settingItem: OneLineWithTaskItem)
}