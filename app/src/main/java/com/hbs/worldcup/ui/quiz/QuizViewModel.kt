package com.hbs.worldcup.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hbs.domain.model.QuizItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : ViewModel(){
    private fun getQuizList() = listOf(QuizItem("1", "1"), QuizItem("2", "2"))

    val quizList = liveData { emit(getQuizList()) }
}