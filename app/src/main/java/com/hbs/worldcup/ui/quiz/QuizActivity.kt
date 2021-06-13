package com.hbs.worldcup.ui.quiz

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseActivity
import com.hbs.worldcup.databinding.QuizActivityBinding
import com.hbs.worldcup.models.ActivityInitializer
import com.hbs.worldcup.models.onError
import com.hbs.worldcup.models.onLoading
import com.hbs.worldcup.models.onSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizActivity : BaseActivity<QuizActivityBinding>() {
    private val viewModel by viewModels<QuizViewModel>()
    private val quizViewPagerAdapter by lazy {
        QuizViewPagerAdapter(viewModel)
    }

    override fun getActivityInitializer(): ActivityInitializer =
        ActivityInitializer(R.layout.quiz_activity, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
        bindView()
        observeViewModel()
        collectQuizList()
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
        viewModel.quizResult.observe(this, { quizResult ->
            quizResult.onSuccess { quizViewPagerAdapter.submitList(it) }
                .onError {  }
                .onLoading {  }
        })

        viewModel.nextStage.observe(this, { stage ->
            if(-1 != stage.position) {
                binding.quizViewpager.currentItem = binding.quizViewpager.currentItem + 1
            }
        })
    }

    private fun collectQuizList() {
        viewModel.requestQuizList()
    }
}