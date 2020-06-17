package com.app.steyrix.githubrepositorysearch.main.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.app.steyrix.githubrepositorysearch.main.data.ApiRepository
import com.app.steyrix.githubrepositorysearch.main.data.GithubApiRepository
import com.app.steyrix.githubrepositorysearch.main.data.api.GithubApiService
import com.app.steyrix.githubrepositorysearch.main.domain.ApiGetReposUseCase
import com.app.steyrix.githubrepositorysearch.main.domain.GithubApiGetReposUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [GithubApiInteractorModule.InteractorModule::class])
class GithubApiInteractorModule {

    @Singleton
    @Provides
    fun provideGithubApiService(context: Application): GithubApiService {
        val cacheSize = 1024 * 1024 * 2
        val cacheObject = Cache(context.cacheDir, cacheSize.toLong())

        val hasNetworkMethod: () -> Boolean = {
            var isConnected = false // Initial Value
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true

            isConnected
        }

        val dispatcher = Dispatcher().apply {
            maxRequests = 50
            maxRequestsPerHost = 10
        }

        val client = OkHttpClient.Builder()
            .cache(cacheObject)
            .addInterceptor {
                var request = it.request()
                request = if (hasNetworkMethod.invoke())
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                it.proceed(request)

            }
            .dispatcher(dispatcher)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
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
        abstract fun bindGetReposUseCase(apiGetReposUseCase: GithubApiGetReposUseCase): ApiGetReposUseCase
    }
}