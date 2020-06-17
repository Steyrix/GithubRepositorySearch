package com.app.steyrix.githubrepositorysearch.main.di

import com.app.steyrix.githubrepositorysearch.main.data.ApiRepository
import com.app.steyrix.githubrepositorysearch.main.data.GithubApiRepository
import com.app.steyrix.githubrepositorysearch.main.data.api.GithubApiService
import com.app.steyrix.githubrepositorysearch.main.domain.ApiGetReposUseCase
import com.app.steyrix.githubrepositorysearch.main.domain.ApiGetUserUseCase
import com.app.steyrix.githubrepositorysearch.main.domain.GithubApiGetReposUseCase
import com.app.steyrix.githubrepositorysearch.main.domain.GithubApiGetUserUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [GithubApiInteractorModule.InteractorModule::class])
class GithubApiInteractorModule {

    @Singleton
    @Provides
    fun provideGithubApiService(): GithubApiService {
        val dispatcher = Dispatcher().apply {
            maxRequests = 50
            maxRequestsPerHost = 10
        }

        val limitedRateClient = OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(limitedRateClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiService::class.java)
    }

    @Module
    abstract class InteractorModule {
        @Singleton
        @Binds
        abstract fun bindApiRepository(apiRepository: GithubApiRepository): ApiRepository

        @Binds
        abstract fun bindGetApiUserUseCase(apiGetUserUseCase: GithubApiGetUserUseCase): ApiGetUserUseCase

        @Binds
        abstract fun bindGetReposUseCase(apiGetReposUseCase: GithubApiGetReposUseCase): ApiGetReposUseCase
    }
}