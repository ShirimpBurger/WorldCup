package com.hbs.worldcup.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hbs.domain.model.LargeCardRecommend
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : ViewModel(){
    val title = liveData { emit("Gamjatwigim!! \uD83D\uDC4B") }
    val largeCardList = liveData { emit(getLargeCardItems()) }

    private fun getLargeCardItems() = listOf(
        LargeCardRecommend("What is favorite food?", "empty", "5", "food"),
        LargeCardRecommend("What is favorite brand?", "empty", "5", "food"),
        LargeCardRecommend("What is favorite game?", "empty", "5", "food")
    )
}