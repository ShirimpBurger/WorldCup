package com.hbs.domain.di

import com.hbs.domain.model.TestData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class RepositoryModule {
    @Provides
    fun provideTestData() : TestData = TestData()
}