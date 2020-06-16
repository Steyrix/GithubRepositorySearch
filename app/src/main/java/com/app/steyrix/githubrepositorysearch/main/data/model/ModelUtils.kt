package com.app.steyrix.githubrepositorysearch.main.data.model

object ModelUtils {
    private const val NOT_FOUND_URL = "404"

    val notFoundUserInfo = UserInfo("Not found", NOT_FOUND_URL)

    fun isNotFound(info: UserInfo):Boolean = info.avatarUrl == NOT_FOUND_URL
}