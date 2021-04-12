package com.hbs.worldcup.ui.quiz

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.hbs.domain.model.core.ActivityInitializer
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseActivity
import com.hbs.worldcup.databinding.QuizActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizActivity : BaseActivity<QuizActivityBinding>() {
    private val viewModel by viewModels<QuizViewModel>()
    private val quizViewPagerAdapter by lazy {
        QuizViewPagerAdapter(nextQuizListener, progressListener)
    }

    private val nextQuizListener = QuizViewPagerAdapter.CompleteQuizListener { position ->
        binding.quizViewpager.currentItem = position + 1
    }

    private val progressListener = QuizViewPagerAdapter.ProgressListener { progress ->
        binding.lottieView.progress = progress
    }

    override fun getActivityInitializer(): ActivityInitializer =
        ActivityInitializer(R.layout.quiz_activity, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
        bindView()
        observeViewModel()
    }

    private fun bindViewModel() {
        binding.viewModel = viewModel
        binding.toolbarTitle = "QuizQuiz"
    }

    private fun bindView() {
        with(binding.quizViewpager) {
            adapter = quizViewPagerAdapter
            orientation = ViewPager2.ORIENTATION_VERTICAL
            isUserInputEnabled = false
        }
    }

    private fun observeViewModel() {
        viewModel.quizList.observe(this, {
            quizViewPagerAdapter.submitList(it)
        })
    }
}