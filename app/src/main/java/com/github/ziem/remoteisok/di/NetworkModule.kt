package com.github.ziem.remoteisok.di

import com.github.ziem.remoteisok.api.RemoteOkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRemoteOkApi(): RemoteOkApi = Retrofit.Builder()
        .baseUrl("https://remoteok.io/")
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
        .create(RemoteOkApi::class.java)
}