package com.hbs.worldcup.ui.quiz

import androidx.lifecycle.*
import com.hbs.data.remote.model.onFailure
import com.hbs.data.remote.model.onLoading
import com.hbs.data.remote.model.onSuccess
import com.hbs.data.remote.repository.GameResultResponse
import com.hbs.domain.mapper.toDomains
import com.hbs.domain.types.GameDataType
import com.hbs.domain.usecase.GameDataUseCase
import com.hbs.worldcup.mappers.toDomain
import com.hbs.worldcup.mappers.toWindowed
import com.hbs.worldcup.models.Game
import com.hbs.worldcup.models.GamePairList
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
    private val _quizResult = MutableLiveData<ViewState<GamePairList>>()
    val quizResult: LiveData<ViewState<GamePairList>> = _quizResult

    private val gameSelector = Channel<Game> { }
    val gameSelectionManager = gameSelector
        .receiveAsFlow()
        .debounce(1000L)
        .buffer(capacity = Channel.BUFFERED)
        .asLiveData()

    fun sendTime(position: Int, time: Long) {
        viewModelScope.launch {
            gameSelectionManager.value
                ?.takeIf { game-> game.position != position && time - game.time > 1000 }
                ?.run { gameSelector.send(this) }
        }
    }

    fun requestQuizList() {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.Main) {
            gameDataUseCase.getGameDatas(GameDataType.FOOD).collect { results: GameResultResponse ->
                results
                    .onSuccess { _quizResult.value = ViewState.Success(it.toDomains().toWindowed()) }
                    .onFailure { _quizResult.value = ViewState.Error(it) }
                    .onLoading { _quizResult.value = ViewState.Loading() }
            }
        }
    }

    fun pick(game: Game) {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            gameDataUseCase.pickGame(game.toDomain())
        }
    }

    fun endGameStageOneLines() {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            gameDataUseCase.getGameDatas(GameDataType.FOOD).collect { gameResultResponse ->
                gameResultResponse
                    .onSuccess {
                        _quizResult.value = ViewState.Success(it.toDomains().toWindowed())
                    }
                    .onFailure { }
                    .onLoading { }
            }
        }
    }
}