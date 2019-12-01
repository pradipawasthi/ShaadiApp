package com.pradip.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import com.pradip.weatherapp.common.Outcome
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.HttpURLConnection

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */

open class BaseViewModel(val compositeDisposable: CompositeDisposable) : ViewModel() {


    protected fun <T> getOutComeError(error: Throwable): Outcome<T> {
        return if (error is HttpException) {
            when (error.response().code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> Outcome.BadRequest(error.message ?: "")
                HttpURLConnection.HTTP_BAD_REQUEST -> Outcome.ApiError(error)
                else -> Outcome.Failure(error)
            }
        } else {
            Outcome.Failure(error)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}