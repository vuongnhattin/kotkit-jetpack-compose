package com.example.kotkit.di

import com.example.kotkit.data.api.service.CommentApiService
import com.example.kotkit.data.api.service.UserApiService
import com.example.kotkit.data.api.service.VideoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideVideoApiService(retrofit: Retrofit): VideoApiService {
        return retrofit.create(VideoApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideCommentApiService(retrofit: Retrofit): CommentApiService {
        return retrofit.create(CommentApiService::class.java)
    }
}