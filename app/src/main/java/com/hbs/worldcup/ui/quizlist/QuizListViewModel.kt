package com.hbs.worldcup.ui.quizlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizListViewModel @Inject constructor() : ViewModel(){
    val title = liveData { emit("QuizList") }
}