package com.hbs.data.local.di

import com.hbs.data.local.database.SettingDatabase
import com.hbs.data.local.repository.SettingRepository
import com.hbs.data.local.repository.SettingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideSettingRepository(settingDatabase: SettingDatabase): SettingRepository = SettingRepositoryImpl(settingDatabase)
}