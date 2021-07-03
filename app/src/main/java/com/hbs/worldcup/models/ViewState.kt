package com.hbs.worldcup.models

 sealed class ViewState<out T : Any> {
    class Success<out T : Any>(val data: T, val status: Int = 200) : ViewState<T>()
    class Error<out T : Any>(val error: Throwable) : ViewState<T>()
    class Loading<out T : Any> : ViewState<T>()
    class NoInternetState<T : Any> : ViewState<T>()
}

inline fun <T : Any> ViewState<T>.onSuccess(action: (T) -> Unit): ViewState<T> {
    if (this is ViewState.Success) action(data)
    return this
}

inline fun <T : Any> ViewState<T>.onError(action: (Throwable) -> Unit): ViewState<T> {
    if(this is ViewState.Error) action(error)
    return this
}

inline fun <T: Any> ViewState<T>.onLoading(action: () -> Unit) : ViewState<T> {
    if(this is ViewState.Loading) action()
    return this
}
