package com.app.steyrix.githubrepositorysearch.main.data.response

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)