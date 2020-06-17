package com.app.steyrix.githubrepositorysearch.main.domain

import androidx.lifecycle.MutableLiveData
import com.app.steyrix.githubrepositorysearch.main.data.response.UserInfo

interface ApiGetUserUseCase {
    fun getUserInfo(userName: String, destination: MutableLiveData<UserInfo>)
}