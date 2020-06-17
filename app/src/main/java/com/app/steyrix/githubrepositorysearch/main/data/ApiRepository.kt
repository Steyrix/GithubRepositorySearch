package com.app.steyrix.githubrepositorysearch.main.data

import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.data.response.UserInfo

interface ApiRepository {
    suspend fun getUserInfo(userName: String): UserInfo
    suspend fun getReposByKeywords(keywords:String): MutableList<RepositoryInfo>
}