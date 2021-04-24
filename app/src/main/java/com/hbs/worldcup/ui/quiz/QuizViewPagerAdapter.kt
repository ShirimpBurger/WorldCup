package com.hbs.worldcup.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hbs.domain.model.QuizItem
import com.hbs.worldcup.databinding.QuizItemBinding
import com.hbs.worldcup.ui.custom.ParallaxScrollingView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class QuizViewPagerAdapter(private val completeQuizListener: CompleteQuizListener, private val progressListener: ProgressListener) :
    ListAdapter<QuizItem, QuizViewPagerAdapter.ViewHolder>(diffUtil) {
    class ViewHolder(val binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parallaxScrollingView = holder.binding.parallaxScrollingView
        parallaxScrollingView.destinationListener = ParallaxScrollingView.DestinationListener {
            completeQuizListener.complete(position)
        }
        parallaxScrollingView.progressListener = ParallaxScrollingView.ProgressListener {
            progressListener.progress(it)
        }
    }

    fun interface CompleteQuizListener {
        fun complete(position: Int)
    }

    fun interface ProgressListener {
        fun progress(progress: Float)
    }
}

private val diffUtil = object :
    DiffUtil.ItemCallback<QuizItem>() {
    override fun areItemsTheSame(oldItem: QuizItem, newItem: QuizItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: QuizItem, newItem: QuizItem): Boolean {
        return oldItem == newItem
    }
}