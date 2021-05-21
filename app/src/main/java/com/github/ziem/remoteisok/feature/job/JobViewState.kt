package com.github.ziem.remoteisok.feature.job

import com.github.ziem.remoteisok.model.Job

data class JobViewState(
    val job: Job? = null,
    val isLoading: Boolean = false,
)