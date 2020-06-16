package com.app.steyrix.githubrepositorysearch.main.view

import androidx.lifecycle.*
import com.app.steyrix.githubrepositorysearch.main.data.model.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.domain.ApiInteractor
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiInteractor: ApiInteractor
) : ViewModel() {

    private val repositories = MutableLiveData<MutableList<RepositoryInfo>>()

    fun getRepositories(): LiveData<MutableList<RepositoryInfo>> {
        return repositories
    }

    fun fetchRepositories(keywords: String) {
        apiInteractor.getRepositoriesByKeywords(keywords, repositories)
    }
}