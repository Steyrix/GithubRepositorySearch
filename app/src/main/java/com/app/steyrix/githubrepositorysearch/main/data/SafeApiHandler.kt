package com.app.steyrix.githubrepositorysearch.main.data

import android.util.Log
import com.app.steyrix.githubrepositorysearch.main.data.response.ResultWrapper
import retrofit2.Response
import java.io.IOException

/**
 * SafeApiHandler is responsible for making safe API calls and managing results.
 */
open class SafeApiHandler {

    suspend fun <T:Any> safeApiCall(
        call: suspend() -> Response<T>,
        errorMsg: String): T? {

        return when(val result = safeApiResult(call, errorMsg)) {
            is ResultWrapper.Success -> {
                result.data
            }
            else -> {
                Log.e(TAG, errorMsg)
                null
            }
        }
    }

    private suspend fun <T:Any> safeApiResult(
        call: suspend() -> Response<T>,
        errorMsg: String): ResultWrapper<T> {

        val response = call.invoke()

        if (response.isSuccessful) {
            return ResultWrapper.Success(response.body()!!)
        }

        return ResultWrapper.Fail(IOException(errorMsg))

    }

    companion object {
        const val TAG = "REPOSITORY"
    }
}