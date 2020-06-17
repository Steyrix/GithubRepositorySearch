package com.app.steyrix.githubrepositorysearch.main.view

import androidx.lifecycle.*
import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.domain.ApiGetReposUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiGetReposUseCase: ApiGetReposUseCase
) : ViewModel() {

    private val repositories = MutableLiveData<MutableList<RepositoryInfo>>()

    fun getRepositories(): LiveData<MutableList<RepositoryInfo>> {
        return repositories
    }

    fun fetchRepositories(keywords: String) {
        apiGetReposUseCase.getReposByKeywords(keywords, repositories)
    }
}