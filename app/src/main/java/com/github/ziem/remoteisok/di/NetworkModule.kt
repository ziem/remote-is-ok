package com.github.ziem.remoteisok.di

import com.github.ziem.remoteisok.api.DateJsonAdapter
import com.github.ziem.remoteisok.api.RemoteOkApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.OffsetDateTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(OffsetDateTime::class.java, DateJsonAdapter())
        .build()

    @Singleton
    @Provides
    fun provideRemoteOkApi(moshi: Moshi): RemoteOkApi = Retrofit.Builder()
        .baseUrl("https://remoteok.io/")
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()
        .create(RemoteOkApi::class.java)
}