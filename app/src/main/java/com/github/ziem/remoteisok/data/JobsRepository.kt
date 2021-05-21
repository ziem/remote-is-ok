package com.github.ziem.remoteisok.data

import com.github.ziem.remoteisok.api.RemoteOkApi
import com.github.ziem.remoteisok.database.JobEntity
import com.github.ziem.remoteisok.database.JobEntityDao
import com.github.ziem.remoteisok.model.Company
import com.github.ziem.remoteisok.model.Job
import com.github.ziem.remoteisok.model.JobResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsRepository @Inject constructor(
    private val remoteOkApi: RemoteOkApi,
    private val jobEntityDao: JobEntityDao,
) {
    fun getJobs(): Flow<List<Job>> = jobEntityDao.getAll().map {
        it.map { jobEntity -> jobEntity.mapToJob() }
    }

    suspend fun syncJobs() {
        val remoteJobs = remoteOkApi.getJobs()
        val validRemoteJobs = remoteJobs.filter { it.isValid() }
        val remoteJobEntities = validRemoteJobs.map { it.mapToJobEntity() }
        jobEntityDao.insertAll(remoteJobEntities)
    }

    suspend fun getJob(jobId: Long): Job {
        return jobEntityDao.find(jobId)
            .mapToJob()
    }

    private fun JobResponse.mapToJobEntity(): JobEntity = JobEntity(
        id,
        date,
        company!!,
        if (company_logo.isNullOrBlank() || company_logo == "https://cdn.sstatic.net/careers/Img/ico-no-company-logo.svg") {
            null
        } else {
            company_logo
        },
        position!!,
        tags.filter { tag -> tag.isNotBlank() },
        description!!,
        location,
        original,
        url
    )

    private fun JobEntity.mapToJob(): Job = Job(
        id,
        position,
        description,
        Company(company, companyLogoUrl),
        url,
        date,
        location,
        tags,
    )
}