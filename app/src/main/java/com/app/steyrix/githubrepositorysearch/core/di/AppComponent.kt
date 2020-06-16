package com.app.steyrix.githubrepositorysearch.core.di

import com.app.steyrix.githubrepositorysearch.main.di.GithubApiInteractorModule
import com.app.steyrix.githubrepositorysearch.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GithubApiInteractorModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}