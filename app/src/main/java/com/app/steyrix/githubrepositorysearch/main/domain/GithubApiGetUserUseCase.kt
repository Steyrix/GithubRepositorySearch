package com.app.steyrix.githubrepositorysearch.main.domain

import androidx.lifecycle.MutableLiveData
import com.app.steyrix.githubrepositorysearch.main.data.ApiRepository
import com.app.steyrix.githubrepositorysearch.main.data.response.UserInfo
import javax.inject.Inject

class GithubApiGetUserUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) : ApiGetUserUseCase, CoroutineBasedInteractor() {

    override fun getUserInfo(userName: String, destination: MutableLiveData<UserInfo>) {
        var response: UserInfo?

        run {
            response = apiRepository.getUserInfo(userName)
            destination.postValue(response)
        }
    }
}