package com.github.ziem.remoteisok.feature.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ziem.remoteisok.data.JobsRepository
import com.github.ziem.remoteisok.model.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val jobsRepository: JobsRepository,
) : ViewModel() {
    private val _jobs = MutableStateFlow<List<Job>>(emptyList())

    val jobs: StateFlow<List<Job>>
        get() = _jobs

    init {
        viewModelScope.launch {
            _jobs.value = jobsRepository.getJobs()
        }
    }
}