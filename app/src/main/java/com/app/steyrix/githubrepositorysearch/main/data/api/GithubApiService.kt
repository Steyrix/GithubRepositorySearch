package com.app.steyrix.githubrepositorysearch.main.data.api

import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryList
import com.app.steyrix.githubrepositorysearch.main.data.response.UserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("users/{user}/")
    suspend fun getUserInfo(
        @Path("user") userName: String
    ): Response<UserInfo>

    @GET("search/repositories")
    suspend fun getRepositoriesByKeywords(
        @Query("q") keywords: String
    ): Response<RepositoryList>
}