package com.app.steyrix.githubrepositorysearch.main.data

import com.app.steyrix.githubrepositorysearch.main.data.api.GithubApiService
import com.app.steyrix.githubrepositorysearch.main.data.response.ResponseUtils
import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.data.response.UserInfo
import javax.inject.Inject

/**
 * GithubApiRepository is responsible for wrapping up API calls.
 */
class GithubApiRepository @Inject constructor(
    private val apiService: GithubApiService
) : SafeApiHandler(), ApiRepository {

    override suspend fun getReposByKeywords(keywords: String): MutableList<RepositoryInfo> {
        val call = apiService.getRepositoriesByKeywords(keywords)
        return safeApiCall({ call }, ERROR_MSG)?.items?.toMutableList() ?: mutableListOf()
    }

    companion object {
        const val ERROR_MSG = "Request to Github API failed"
    }
}