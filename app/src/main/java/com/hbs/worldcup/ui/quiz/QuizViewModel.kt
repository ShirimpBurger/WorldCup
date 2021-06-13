package com.hbs.worldcup.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hbs.data.remote.model.onFailure
import com.hbs.data.remote.model.onLoading
import com.hbs.data.remote.model.onSuccess
import com.hbs.data.remote.repository.GameEntityResults
import com.hbs.worldcup.models.GameLayoutPairList
import com.hbs.domain.types.GameDataType
import com.hbs.domain.usecase.GameDataUseCase
import com.hbs.worldcup.core.Event
import com.hbs.worldcup.mappers.toWindowed
import com.hbs.worldcup.models.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val gameDataUseCase: GameDataUseCase) :
    ViewModel() {
    private val _quizResult = MutableLiveData<ViewState<GameLayoutPairList>>()
    val quizResult: LiveData<ViewState<GameLayoutPairList>> = _quizResult

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

    fun requestQuizList() {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.Main) {
            gameDataUseCase.getAllData(GameDataType.FOOD)
                .collect { results: GameEntityResults ->
                    results
                        .onSuccess { _quizResult.value = ViewState.Success(it.toWindowed()) }
                        .onFailure { _quizResult.value = ViewState.Error(it) }
                        .onLoading { _quizResult.value = ViewState.Loading() }
                }
        }
    }
}