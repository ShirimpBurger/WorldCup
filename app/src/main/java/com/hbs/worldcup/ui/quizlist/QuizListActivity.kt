package com.hbs.worldcup.ui.quizlist

import android.os.Bundle
import androidx.activity.viewModels
import com.hbs.domain.model.core.ActivityInitializer
import com.hbs.worldcup.R
import com.hbs.worldcup.core.BaseActivity
import com.hbs.worldcup.databinding.QuizListActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizListActivity : BaseActivity<QuizListActivityBinding>() {
    private val quizListViewModel by viewModels<QuizListViewModel>()

    override fun getActivityInitializer() = ActivityInitializer(R.layout.quiz_list_activity, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() {
        binding.viewModel = quizListViewModel
    }
}