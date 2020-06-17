package com.app.steyrix.githubrepositorysearch.main.domain

import androidx.lifecycle.MutableLiveData
import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryInfo

interface ApiGetReposUseCase {
    fun getReposByKeywords(keywords: String, destination: MutableLiveData<MutableList<RepositoryInfo>>)
}