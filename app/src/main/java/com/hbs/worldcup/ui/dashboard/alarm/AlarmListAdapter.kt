package com.hbs.worldcup.ui.dashboard.alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hbs.domain.model.AlarmItem
import com.hbs.worldcup.databinding.AlarmItemBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class AlarmListAdapter : ListAdapter<AlarmItem, AlarmListAdapter.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AlarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.alarmItem = getItem(position)
    }

    class ViewHolder(val binding: AlarmItemBinding) : RecyclerView.ViewHolder(binding.root)

}

private val diffUtil = object :
    DiffUtil.ItemCallback<AlarmItem>() {
    override fun areItemsTheSame(oldItem: AlarmItem, newItem: AlarmItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AlarmItem, newItem: AlarmItem): Boolean {
        return oldItem == newItem
    }
}