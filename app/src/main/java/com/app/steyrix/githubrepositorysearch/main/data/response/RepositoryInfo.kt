package com.app.steyrix.githubrepositorysearch.main.data.response

data class RepositoryInfo(
    val name: String,
    val language: String,
    val owner: UserInfo
)