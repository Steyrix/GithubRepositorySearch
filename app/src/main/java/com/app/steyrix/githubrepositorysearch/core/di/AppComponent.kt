package com.app.steyrix.githubrepositorysearch.core.di

import android.app.Application
import com.app.steyrix.githubrepositorysearch.main.di.GithubApiInteractorModule
import com.app.steyrix.githubrepositorysearch.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GithubApiInteractorModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }
}