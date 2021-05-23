package com.hbs.worldcup.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hbs.domain.usecase.SettingUseCase
import com.hbs.worldcup.core.Event
import com.hbs.worldcup.mappers.toDomain
import com.hbs.worldcup.mappers.toPresentation
import com.hbs.worldcup.models.OneLineWithTaskItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val settingUseCase: SettingUseCase) :
    ViewModel() {
    private val selectEvent = MutableLiveData(Event(false))
    val settingItems = Transformations.switchMap(selectEvent) { _ ->
        flow { emit(settingUseCase.getAllSettingItems().map { it.toPresentation() }) }.asLiveData()
    }

    fun insertAndSelectedAllItems(presentation: OneLineWithTaskItem) {
        runBlocking {
            settingUseCase.insertToggleData(presentation.toDomain())
            selectEvent.postValue(Event(true))
        }
    }
}