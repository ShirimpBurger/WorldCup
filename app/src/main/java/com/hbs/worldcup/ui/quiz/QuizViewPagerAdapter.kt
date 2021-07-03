package com.hbs.worldcup.ui.quiz

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hbs.worldcup.databinding.QuizItemBinding
import com.hbs.worldcup.models.GamePair
import kotlinx.coroutines.*
import java.util.*

class QuizViewPagerAdapter(
    private val viewModel: QuizViewModel,
) :
    ListAdapter<GamePair, QuizViewPagerAdapter.ViewHolder>(diffUtil) {
    private val deviceWidth by lazy {
        Resources.getSystem().displayMetrics.widthPixels
    }

    private val job = mutableListOf<Job>()

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
        holder.binding.quizItem = getItem(position)
        val parallaxScrollingView = holder.binding.scrollView
        parallaxScrollingView.post {
            parallaxScrollingView.scrollTo(deviceWidth / 2, 0)
        }

        parallaxScrollingView.setOnScrollChangeListener { _, scrollX, _, _, _ ->
            if (scrollX < 50) {
                job.add(callNextStage(position))
            } else if (scrollX >= deviceWidth - 50) {
                job.add(callNextStage(position))
            } else {
                job.forEach { it.cancel() }
                viewModel.sendTime(position, -99999999999999L)
            }
            if (deviceWidth / 2 > scrollX) {
                holder.binding.selectionAImageView.translationX = (scrollX / 2).toFloat()
            } else {
                holder.binding.selectionAImageView.translationX =
                    (deviceWidth / 2) - (scrollX / 2).toFloat()
            }
        }
    }

    private fun callNextStage(position: Int): Job {
        return GlobalScope.launch {
            viewModel.sendTime(position, Date().time)
            delay(1_000L)
            if (isActive) {
                viewModel.sendTime(position, Date().time)
            }
        }
    }

    class ViewHolder(val binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root)

}

private val diffUtil = object :
    DiffUtil.ItemCallback<GamePair>() {
    override fun areItemsTheSame(oldItem: GamePair, newItem: GamePair): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GamePair, newItem: GamePair): Boolean {
        return oldItem == newItem
    }
}