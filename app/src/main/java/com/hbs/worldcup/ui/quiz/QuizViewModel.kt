package com.hbs.worldcup.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hbs.domain.model.QuizItem
import com.hbs.worldcup.core.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : ViewModel() {
    private fun getQuizList() = listOf(QuizItem("1", "1"), QuizItem("2", "2"), QuizItem("3", "3"))
    val quizList = liveData { emit(getQuizList()) }
    private val _nextStage = MutableLiveData(Event("STOP"))
    val nextStage: LiveData<Event<String>> = _nextStage

    private var tempTime = 9999999999999L

    fun sendTime(time: Long) {
        when {
            time - tempTime > 1200L -> {
                tempTime = time
            }
            time - tempTime in 0..1200L -> {
                _nextStage.value = Event("GO")

            }
            else -> {
                tempTime = time
            }
        }
    }
}