package com.bbrustol.core.data.di

import com.bbrustol.core.data.remote.newsapi.JSONPlaceholderApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Singleton
    @Provides
    fun provideJSONPlaceholderApiService(retrofit: Retrofit): JSONPlaceholderApiService =
        retrofit.create(JSONPlaceholderApiService::class.java)

}
