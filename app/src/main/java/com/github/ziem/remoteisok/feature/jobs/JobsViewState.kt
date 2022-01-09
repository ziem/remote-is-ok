package com.github.ziem.remoteisok.feature.jobs

import com.github.ziem.remoteisok.model.Job

data class JobsViewState(
    val jobs: List<Job> = emptyList(),
    val filteredJobs: List<Job> = emptyList(),
    val isLoading: Boolean = false,
    val query: String? = null
)