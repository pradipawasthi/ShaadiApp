package com.pradip.weatherapp.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<Outcome<T>>.observeK(owner: LifecycleOwner,
                                             crossinline success: (T) -> Unit = {},
                                             crossinline apiError: () -> Unit = {},
                                             crossinline unAuthorizedCallback: () -> Unit = {},
                                             crossinline ioExceptionCallback: () -> Unit = {},
                                             crossinline progressStateCallback: (Boolean) -> Unit = {},
                                             crossinline connectionTimeoutCallback: () -> Unit = {}) {
    this.observe(owner, Observer {
        when (it) {
            is Outcome.Success -> success(it.data)
            is Outcome.Failure -> ioExceptionCallback()
            is Outcome.BadRequest -> unAuthorizedCallback()
            is Outcome.ApiError -> apiError()
            is Outcome.Progress -> progressStateCallback(it.loading)
        }
    })
}