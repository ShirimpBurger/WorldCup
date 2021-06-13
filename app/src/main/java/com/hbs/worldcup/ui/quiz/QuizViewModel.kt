package com.hbs.worldcup.ui.quiz

import androidx.lifecycle.*
import com.hbs.data.remote.model.onFailure
import com.hbs.data.remote.model.onLoading
import com.hbs.data.remote.model.onSuccess
import com.hbs.data.remote.repository.GameEntityResults
import com.hbs.domain.types.GameDataType
import com.hbs.domain.usecase.GameDataUseCase
import com.hbs.worldcup.mappers.toWindowed
import com.hbs.worldcup.models.GameLayoutPairList
import com.hbs.worldcup.models.Stage
import com.hbs.worldcup.models.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val gameDataUseCase: GameDataUseCase) :
    ViewModel() {
    private val _quizResult = MutableLiveData<ViewState<GameLayoutPairList>>()
    val quizResult: LiveData<ViewState<GameLayoutPairList>> = _quizResult

    private val nextStageChannel = Channel<Stage> { }
    val nextStage = nextStageChannel
        .receiveAsFlow()
        .debounce(1000L)
        .buffer(capacity = Channel.BUFFERED)
        .asLiveData()

    init {
        viewModelScope.launch {
            nextStageChannel.send(Stage(-1, -1))
        }
    }

    fun sendTime(position: Int, time: Long) {
        viewModelScope.launch {
            val tempStage = nextStage.value?: return@launch
            if (tempStage.position != position && time - tempStage.time > 1000) {
                nextStageChannel.send(Stage(position, time))
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