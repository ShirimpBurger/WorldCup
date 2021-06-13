package com.hbs.domain.di

import com.hbs.data.local.repository.SettingRepository
import com.hbs.data.remote.repository.GameRepository
import com.hbs.domain.usecase.GameDataUseCase
import com.hbs.domain.usecase.GameDataUseCaseImpl
import com.hbs.domain.usecase.SettingUseCase
import com.hbs.domain.usecase.SettingUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSettingUseCase(settingRepository: SettingRepository) : SettingUseCase = SettingUseCaseImpl(settingRepository)

    @Provides
    fun provideGameDataUseCase(gameRepository: GameRepository) : GameDataUseCase = GameDataUseCaseImpl(gameRepository)
}