package com.hbs.data.all.di

import android.content.Context
import com.hbs.data.local.repository.SharedPreferenceRepository
import com.hbs.data.local.repository.SharedPreferenceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Provides
    fun provideSharedPreferenceRepository(@ApplicationContext context: Context) : SharedPreferenceRepository {
        return SharedPreferenceRepositoryImpl(context)
    }
}