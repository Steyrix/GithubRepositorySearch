package com.app.steyrix.githubrepositorysearch.main.domain

import androidx.lifecycle.MutableLiveData
import com.app.steyrix.githubrepositorysearch.main.data.model.RepositoryInfo
import com.app.steyrix.githubrepositorysearch.main.data.model.UserInfo

interface ApiInteractor {
    fun getUserInfo(userName: String, destination: MutableLiveData<UserInfo>)
    fun getRepositoriesByKeywords(keywords: String, destination: MutableLiveData<MutableList<RepositoryInfo>>)
}