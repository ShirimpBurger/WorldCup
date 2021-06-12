package com.hbs.data.remote.model

sealed class ResultResponse<out T : Any> {
    data class Success<out T : Any>(val status: Int, val data: T) : ResultResponse<T>()
    data class Failure(val error: Exception) : ResultResponse<Nothing>()
    object Loading : ResultResponse<Nothing>()
}

inline fun <T : Any> ResultResponse<T>.onSuccess(action: (T) -> Unit): ResultResponse<T> {
    if (this is ResultResponse.Success) action(data)
    return this
}

inline fun <T : Any> ResultResponse<T>.onFailure(action: (Throwable) -> Unit): ResultResponse<T> {
    if(this is ResultResponse.Failure) action(error)
    return this
}

inline fun <T: Any> ResultResponse<T>.onLoading(action: () -> Unit) : ResultResponse<T> {
    if(this is ResultResponse.Loading) action()
    return this
}