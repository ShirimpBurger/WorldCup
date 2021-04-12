package com.hbs.worldcup.ui.quizlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hbs.domain.model.core.ActivityInitializer
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseActivity
import com.hbs.worldcup.databinding.QuizListActivityBinding
import com.hbs.worldcup.ui.quiz.QuizActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizListActivity : BaseActivity<QuizListActivityBinding>() {
    private val quizListViewModel by viewModels<QuizListViewModel>()
    private val largeCardRecommendAdapter by lazy { LargeCardRecommendAdapter(recommendClickCallback)}

    override fun getActivityInitializer() = ActivityInitializer(R.layout.quiz_list_activity, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
        bindView()
        observeViewModel()
    }

    private fun bindViewModel() {
        binding.viewModel = quizListViewModel
    }

    private fun bindView(){
        with(binding.largeCardRecommendRecyclerview) {
            adapter = largeCardRecommendAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeViewModel() {
        quizListViewModel.largeCardRecommends.observe(this, {
            largeCardRecommendAdapter.submitList(it)
        })
    }

    private val recommendClickCallback = LargeCardRecommendAdapter.Callback { largeCardRecommend, _ ->
        startActivity(Intent(this, QuizActivity::class.java))
    }
}