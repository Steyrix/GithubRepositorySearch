package com.app.steyrix.githubrepositorysearch.main.domain

import androidx.lifecycle.MutableLiveData
import com.app.steyrix.githubrepositorysearch.main.data.ApiRepository
import com.app.steyrix.githubrepositorysearch.main.data.model.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.data.model.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * GithubApiInteractor is the part of domain layer, which is responsible for multithreading.
 * It encapsulates coroutines' logic.
 */
class GithubApiInteractor @Inject constructor(
    private val apiRepository: ApiRepository
) : ApiInteractor {
    
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    override fun getUserInfo(userName: String, destination: MutableLiveData<UserInfo>) {
        var response: UserInfo?

        scope.launch {
            response = apiRepository.getUserInfo(userName)
            destination.postValue(response)
        }
    }

    override fun getRepositoriesByKeywords(
        keywords: String,
        destination: MutableLiveData<MutableList<RepositoryInfo>>
    ) {
        var response: MutableList<RepositoryInfo>?

        scope.launch {
            response = apiRepository.getRepositoriesByKeywords(keywords)
            destination.postValue(response)
        }
    }
}