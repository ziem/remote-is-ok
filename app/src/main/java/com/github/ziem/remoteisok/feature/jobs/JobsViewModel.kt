package com.github.ziem.remoteisok.feature.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ziem.remoteisok.data.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val jobsRepository: JobsRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(JobsViewState(isLoading = true))

    val state: StateFlow<JobsViewState>
        get() = _state

    init {
        observeJobs()
        refreshJobs()
    }

    private fun observeJobs() {
        viewModelScope.launch {
            jobsRepository.getJobs()
                .collect { _state.emit(_state.value.copy(jobs = it, isLoading = false)) }
        }
    }

    fun refreshJobs() {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isLoading = true))
            jobsRepository.syncJobs()
            _state.emit(_state.value.copy(isLoading = false))
        }
    }
}