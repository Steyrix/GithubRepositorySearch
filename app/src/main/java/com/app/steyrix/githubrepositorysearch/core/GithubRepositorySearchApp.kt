package com.app.steyrix.githubrepositorysearch.core

import android.app.Application
import com.app.steyrix.githubrepositorysearch.core.di.AppComponent
import com.app.steyrix.githubrepositorysearch.core.di.DaggerAppComponent

class GithubRepositorySearchApp : Application() {
    val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()
}