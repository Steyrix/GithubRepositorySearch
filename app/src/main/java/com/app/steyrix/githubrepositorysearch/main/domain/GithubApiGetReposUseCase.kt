package com.app.steyrix.githubrepositorysearch.main.domain

import androidx.lifecycle.MutableLiveData
import com.app.steyrix.githubrepositorysearch.main.data.ApiRepository
import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryInfo
import javax.inject.Inject

class GithubApiGetReposUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) : ApiGetReposUseCase, CoroutineBasedInteractor() {

    override fun getReposByKeywords(keywords: String, destination: MutableLiveData<MutableList<RepositoryInfo>>) {
        var response: MutableList<RepositoryInfo>?

        run {
            response = apiRepository.getReposByKeywords(keywords)
            destination.postValue(response)
        }
    }
}