package com.github.ziem.remoteisok.feature.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ziem.remoteisok.data.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val jobsRepository: JobsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(JobViewState())

    val state: StateFlow<JobViewState>
        get() = _state

    fun loadJob(jobId: Long) {
        viewModelScope.launch {
            _state.emit(_state.value.copy(isLoading = true))
            _state.emit(_state.value.copy(job = jobsRepository.getJob(jobId), isLoading = false))
        }
    }
}