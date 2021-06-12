package com.hbs.data.all.di

import com.hbs.data.local.database.SettingDatabase
import com.hbs.data.local.repository.SettingRepository
import com.hbs.data.local.repository.SettingRepositoryImpl
import com.hbs.data.remote.repository.GameRepository
import com.hbs.data.remote.repository.GameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideSettingRepository(settingDatabase: SettingDatabase): SettingRepository = SettingRepositoryImpl(settingDatabase)

    @Provides
    fun provideFoodRepository() : GameRepository = GameRepositoryImpl()
}