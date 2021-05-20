package com.hbs.worldcup.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hbs.domain.model.LargeCardRecommend
import com.hbs.worldcup.databinding.LargeCardItemBinding

class DashboardListAdapter(private val callback: Callback) :
    ListAdapter<LargeCardRecommend, DashboardListAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(val binding: LargeCardItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LargeCardItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            item = getItem(position)
            root.setOnClickListener { callback.click(getItem(position), position) }
            executePendingBindings()
        }
    }

    fun interface Callback {
        fun click(largeCardRecommend: LargeCardRecommend, position: Int)
    }

}

private val diffUtil = object : DiffUtil.ItemCallback<LargeCardRecommend>() {
    override fun areItemsTheSame(
        oldItem: LargeCardRecommend,
        newItem: LargeCardRecommend
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: LargeCardRecommend,
        newItem: LargeCardRecommend
    ): Boolean {
        return oldItem == newItem
    }
}