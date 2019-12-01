package com.pradip.shaadi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doubtnutapp.domain.similarVideo.interactor.GetCandidateDetailsUseCase
import com.pradip.domain.entities.CandidateDetailsEntity
import com.pradip.shaadi.dataModel.CandidateDetailsDataModel
import com.pradip.shaadi.mapper.CandidateDetailsDataMapper
import com.pradip.shaadi.plus
import com.pradip.shaadi.common.Outcome
import com.pradip.shaadi.common.applyIoToMainSchedulerOnSingle
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */

class MainViewModel @Inject constructor(
    private val getCandidateDetailsUseCase: GetCandidateDetailsUseCase,
    private val candidateDetailsDataMapper: CandidateDetailsDataMapper,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    companion object {
        const val PAGE = 4
    }


    private val _getWeatherForecastData = MutableLiveData<Outcome<CandidateDetailsDataModel>>()
    val getCandidateDetails: LiveData<Outcome<CandidateDetailsDataModel>>
        get() = _getWeatherForecastData

    fun getWeatherforeCast() {
        _getWeatherForecastData.value = Outcome.loading(true)
        compositeDisposable + getCandidateDetailsUseCase
            .execute(GetCandidateDetailsUseCase.Param(PAGE))
            .applyIoToMainSchedulerOnSingle()
            .subscribe(this::onSuccess, this::onError)


    }

    private fun onSuccess(candidateDetailsEntity: CandidateDetailsEntity) {

        _getWeatherForecastData.value = Outcome.loading(false)
        val weatherForecastData = candidateDetailsEntity.run {
            candidateDetailsDataMapper.map(candidateDetailsEntity)
        }

        _getWeatherForecastData.value = Outcome.success(weatherForecastData)
    }


    private fun onError(error: Throwable) {
        _getWeatherForecastData.value = Outcome.loading(false)
        _getWeatherForecastData.value = if (error is HttpException) {
            when (error.response().code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> Outcome.BadRequest(error.message ?: "")
                HttpURLConnection.HTTP_BAD_REQUEST -> Outcome.ApiError(error)
                else -> Outcome.Failure(error)
            }
        } else {
            Outcome.Failure(error)
        }
    }

}
