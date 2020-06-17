package com.app.steyrix.githubrepositorysearch.main.data

import com.app.steyrix.githubrepositorysearch.main.data.response.RepositoryInfo

interface ApiRepository {
    suspend fun getReposByKeywords(keywords:String): MutableList<RepositoryInfo>
}