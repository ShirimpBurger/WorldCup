package com.hbs.data.all.di

import com.hbs.data.local.database.LocalGameDatabase
import com.hbs.data.local.repository.GameLocalRepository
import com.hbs.data.local.repository.GameLocalRepositoryImpl
import com.hbs.data.local.repository.SettingRepository
import com.hbs.data.local.repository.SettingRepositoryImpl
import com.hbs.data.remote.repository.GameRemoteRepository
import com.hbs.data.remote.repository.GameRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideSettingRepository(localGameDatabase: LocalGameDatabase): SettingRepository =
        SettingRepositoryImpl(localGameDatabase)

    @Provides
    fun provideGameRemoteRepository(): GameRemoteRepository = GameRemoteRepositoryImpl()

    @Provides
    fun provideGameLocalRepository(localGameDatabase: LocalGameDatabase): GameLocalRepository =
        GameLocalRepositoryImpl(localGameDatabase)
}