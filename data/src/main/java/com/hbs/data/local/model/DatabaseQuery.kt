package com.hbs.data.local.model

object DatabaseQueryConfig {
    const val SUCCESS = 200
    const val EMPTY_SIZE_EXCEPTION = 400
    const val ILLEGAL_SIZE_EXCEPTION = 404
    const val TIME_OUT_EXCEPTION = 408
}

sealed class DatabaseQuery<out T : Any> {
    data class Success<out T : Any>(val status: Int, val data: T) : DatabaseQuery<T>()
    data class Failure(val status: Int) : DatabaseQuery<Nothing>()
    object Loading : DatabaseQuery<Nothing>()
}

inline fun <T : Any> DatabaseQuery<T>.onHit(action: (T) -> Unit): DatabaseQuery<T> {
    if (this is DatabaseQuery.Success) action(data)
    return this
}

inline fun <T : Any> DatabaseQuery<T>.onHitFailure(action: () -> Unit): DatabaseQuery<T> {
    if(this is DatabaseQuery.Failure) action()
    return this
}

inline fun <T: Any> DatabaseQuery<T>.onLoading(action: () -> Unit) : DatabaseQuery<T> {
    if(this is DatabaseQuery.Loading) action()
    return this
}