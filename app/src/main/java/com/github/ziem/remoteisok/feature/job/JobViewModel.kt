package com.github.ziem.remoteisok.feature.job

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ziem.remoteisok.data.JobsRepository
import com.github.ziem.remoteisok.model.Company
import com.github.ziem.remoteisok.model.Job
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class JobViewModel @ViewModelInject constructor(private val jobsRepository: JobsRepository) :
    ViewModel() {
    private val _job = MutableStateFlow(Job.empty())

    val job: StateFlow<Job>
        get() = _job

    init {
        viewModelScope.launch {
            // TODO: fetch from repository
            val jobResponse = Job(
                "Senior Software Developer",
                "Shopify is now permanently remote and working towards a future that is digital by default. Learn more about what this can mean for you.",
                Company(
                    "Shopify",
                    "https://remoteok.io/assets/jobs/99281a114893cc6c5b949f646c0ef1e61607980072.png"
                ),
                "https://remoteok.io/remote-jobs/100867",
                "2020-12-15T22:59:52+00:00",
                "Worldwide",
                listOf("html", "ruby", "react", "react native")
            )
            _job.value = jobResponse
        }
    }
}