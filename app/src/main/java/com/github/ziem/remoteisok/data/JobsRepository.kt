package com.github.ziem.remoteisok.data

import com.github.ziem.remoteisok.api.RemoteOkApi
import com.github.ziem.remoteisok.mapper.asJob
import com.github.ziem.remoteisok.model.Job
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsRepository @Inject constructor(private val remoteOkApi: RemoteOkApi) {
    suspend fun getJobs(): List<Job> = remoteOkApi.getJobs()
        .filter { it.isValid() }
        .map { it.asJob() }
}