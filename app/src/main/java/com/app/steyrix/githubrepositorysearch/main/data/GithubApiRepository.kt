package com.app.steyrix.githubrepositorysearch.main.data

import com.app.steyrix.githubrepositorysearch.main.data.api.GithubApiService
import com.app.steyrix.githubrepositorysearch.main.data.model.ModelUtils
import com.app.steyrix.githubrepositorysearch.main.data.model.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.data.model.UserInfo
import javax.inject.Inject

/**
 * GithubApiRepository is responsible for wrapping up API calls.
 */
class GithubApiRepository @Inject constructor(
    private val apiService: GithubApiService
) : SafeApiHandler(), ApiRepository {

    override suspend fun getUserInfo(userName: String): UserInfo {
        val call = apiService.getUserInfo(userName)
        return safeApiCall({ call }, ERROR_MSG) ?: ModelUtils.notFoundUserInfo
    }

    override suspend fun getRepositoriesByKeywords(keywords: String): MutableList<RepositoryInfo> {
        val call = apiService.getRepositoriesByKeywords(keywords)
        return safeApiCall({ call }, ERROR_MSG)?.items?.toMutableList() ?: mutableListOf()
    }

    companion object {
        const val ERROR_MSG = "Request to Github API failed"
    }
}