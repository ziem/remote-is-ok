package com.github.ziem.remoteisok.api

import com.github.ziem.remoteisok.model.JobResponse
import retrofit2.http.GET

interface RemoteOkApi {
    @GET("/api")
    suspend fun getJobs(): List<JobResponse>
}