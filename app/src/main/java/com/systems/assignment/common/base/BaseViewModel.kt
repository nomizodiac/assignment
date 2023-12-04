package com.systems.assignment.common.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    private lateinit var jobList: ArrayList<Job>

    protected fun cancelJob(job: Job?) {
        job?.cancel()
    }

    protected fun addJob(job: Job?) {
        if (!::jobList.isInitialized) {
            jobList = ArrayList()
        }
        job?.let {
            jobList.add(job)
        }
    }

    protected fun removeJob(job: Job?) {
        if (::jobList.isInitialized) {
            job?.let {
                jobList.remove(it)
            }
        }
    }

    override fun onCleared() {
        if (::jobList.isInitialized) {
            jobList.forEach {
                if (it.isActive) it.cancel()
            }
        }
        super.onCleared()
    }
}