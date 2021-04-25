package com.hbs.worldcup.ui.quizlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hbs.domain.model.LargeCardRecommend
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizListViewModel @Inject constructor() : ViewModel(){
    val title = liveData { emit("Gamjatwigim!! \uD83D\uDC4B") }
    val largeCardRecommends = liveData { emit(getLargeCardItems()) }

    private fun getLargeCardItems() = listOf(
        LargeCardRecommend("What is favorite food?", "empty", "5", "food"),
        LargeCardRecommend("What is favorite brand?", "empty", "5", "food"),
        LargeCardRecommend("What is favorite game?", "empty", "5", "food")
    )
}