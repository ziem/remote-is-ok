package com.github.ziem.remoteisok.di

import android.content.Context
import androidx.room.Room
import com.github.ziem.remoteisok.database.AppDatabase
import com.github.ziem.remoteisok.database.JobEntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "remote-is-ok")
        .build()

    @Singleton
    @Provides
    fun provideJobEntityDao(appDatabase: AppDatabase): JobEntityDao = appDatabase.jobDao()
}