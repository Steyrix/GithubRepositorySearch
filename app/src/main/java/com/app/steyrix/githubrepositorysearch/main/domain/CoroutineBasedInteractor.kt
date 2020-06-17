package com.app.steyrix.githubrepositorysearch.main.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class CoroutineBasedInteractor {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    protected fun run( block: suspend () -> Unit) {
        scope.launch {
            block.invoke()
        }
    }
}