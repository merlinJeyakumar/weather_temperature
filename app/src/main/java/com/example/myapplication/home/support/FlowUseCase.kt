package com.example.myapplication.home.support

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in P, T> {

    operator fun invoke(parameters: P): Flow<NetworkResult<T>> {
        return performAction(parameters)
    }

    protected abstract fun performAction(parameters: P): Flow<NetworkResult<T>>

}

sealed class NetworkResult<out T>

data class StatusApiResult<out T>(
    val state: Status,
) : NetworkResult<T>()

data class ErrorApiResult<out T>(
    val message: String,
    val throwable: Throwable? = null,
    val exception: Exception? = null,
) : NetworkResult<T>()

data class SuccessApiResult<out T>(
    val data: T,
    val state: Int? = null,
) : NetworkResult<T>()


sealed class Status
data class Information<T>(val message: String, val result: T) : Status()
data class Error<out T>(val exception: T) : Status()
data class Progress<out T>(val progress: T) : Status()