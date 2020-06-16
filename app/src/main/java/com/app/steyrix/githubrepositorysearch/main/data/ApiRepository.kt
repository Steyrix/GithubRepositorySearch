package com.app.steyrix.githubrepositorysearch.main.data

import com.app.steyrix.githubrepositorysearch.main.data.model.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.data.model.UserInfo

interface ApiRepository {
    suspend fun getUserInfo(userName: String): UserInfo
    suspend fun getRepositoriesByKeywords(keywords:String): MutableList<RepositoryInfo>
}