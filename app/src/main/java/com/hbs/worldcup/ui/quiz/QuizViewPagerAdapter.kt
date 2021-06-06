package com.hbs.worldcup.ui.quiz

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hbs.domain.model.QuizItem
import com.hbs.worldcup.databinding.QuizItemBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class QuizViewPagerAdapter(
    private val completeQuizListener: CompleteQuizListener
) :
    ListAdapter<QuizItem, QuizViewPagerAdapter.ViewHolder>(diffUtil) {
    class ViewHolder(val binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val deviceWidth by lazy {
        Resources.getSystem().displayMetrics.widthPixels
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.selectionAImageView.layoutParams = binding.selectionAImageView.layoutParams.apply {
            width = deviceWidth
        }
        binding.selectionBImageView.layoutParams = binding.selectionBImageView.layoutParams.apply {
            width = deviceWidth
        }
        return ViewHolder(binding)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parallaxScrollingView = holder.binding.scrollView
        parallaxScrollingView.post {
            parallaxScrollingView.scrollTo(deviceWidth / 2, 0)
        }

        parallaxScrollingView.setOnScrollChangeListener { _, scrollX, _, _, _ ->
            if (scrollX == 0) {
                parallaxScrollingView.postDelayed({
                    if(scrollX < 50) {
                        completeQuizListener.complete(position + 1)
                    }
                }, 1000L)
                completeQuizListener.complete(position + 1)
            } else if (scrollX >= deviceWidth) {
                parallaxScrollingView.postDelayed({
                    if(scrollX > deviceWidth - 50) {
                        completeQuizListener.complete(position + 1)
                    }
                }, 1000L)
                completeQuizListener.complete(position + 1)
            }
            if (deviceWidth / 2 > scrollX) {
                holder.binding.selectionAImageView.translationX = (scrollX / 2).toFloat()
            } else {
                holder.binding.selectionAImageView.translationX =
                    (deviceWidth / 2) - (scrollX / 2).toFloat()
            }
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