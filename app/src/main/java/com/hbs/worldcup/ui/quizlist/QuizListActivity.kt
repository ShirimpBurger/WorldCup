package com.hbs.worldcup.ui.quizlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hbs.worldcup.databinding.QuizListActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizListActivity : AppCompatActivity() {
    private val quizListViewModel by viewModels<QuizListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = QuizListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModel = quizListViewModel
    }
}