package com.github.ziem.remoteisok.feature.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ziem.remoteisok.data.JobsRepository
import com.github.ziem.remoteisok.model.Company
import com.github.ziem.remoteisok.model.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
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
            // TODO: fetch from repository
            val job = Job(
                1,
                "Senior Software Developer",
                "Shopify is now permanently remote and working towards a future that is digital by default. Learn more about what this can mean for you.",
                Company(
                    "Shopify",
                    "https://remoteok.io/assets/jobs/99281a114893cc6c5b949f646c0ef1e61607980072.png"
                ),
                "https://remoteok.io/remote-jobs/100867",
                OffsetDateTime.now(),
                "Worldwide",
                listOf("html", "ruby", "react", "react native")
            )
            _state.value = _state.value.copy(isLoading = true)
            _state.value = _state.value.copy(job = job, isLoading = false)
        }
    }
}