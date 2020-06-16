package com.app.steyrix.githubrepositorysearch.core

import android.app.Application
import com.app.steyrix.githubrepositorysearch.core.di.DaggerAppComponent

class GithubRepositorySearchApp : Application() {
    val appComponent = DaggerAppComponent.create()
}