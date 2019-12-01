package com.pradip.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doubtnutapp.domain.similarVideo.interactor.GetWeatherForecastUseCase
import com.pradip.domain.entities.WeatherForecastEntity
import com.pradip.weatherapp.dataModel.WeatherForecastDataModel
import com.pradip.weatherapp.mapper.WeatherForecastDataMapper
import com.pradip.weatherapp.plus
import com.pradip.weatherapp.common.Outcome
import com.pradip.weatherapp.common.applyIoToMainSchedulerOnSingle
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

/**
 *  Created by Pradip Awasthi on 2019-09-14.
 */

class MainViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val weatherForecastDataMapper: WeatherForecastDataMapper,
    compositeDisposable: CompositeDisposable
) : BaseViewModel(compositeDisposable) {

    companion object {
        const val PAGE = 4
    }


    private val _getWeatherForecastData = MutableLiveData<Outcome<WeatherForecastDataModel>>()
    val getWeatherForecastData: LiveData<Outcome<WeatherForecastDataModel>>
        get() = _getWeatherForecastData

    fun getWeatherforeCast(city: String, latitude: Double, longitude: Double) {
        _getWeatherForecastData.value = Outcome.loading(true)
        compositeDisposable + getWeatherForecastUseCase
            .execute(GetWeatherForecastUseCase.Param(city, latitude, longitude, PAGE))
            .applyIoToMainSchedulerOnSingle()
            .subscribe(this::onSuccess, this::onError)


    }

    private fun onSuccess(weatherForecastEntity: WeatherForecastEntity) {

        _getWeatherForecastData.value = Outcome.loading(false)
        val weatherForecastData = weatherForecastEntity.run {
            weatherForecastDataMapper.map(weatherForecastEntity)
        }

        _getWeatherForecastData.value = Outcome.success(weatherForecastData)
    }


    private fun onError(error: Throwable) {
        _getWeatherForecastData.value = Outcome.loading(false)
        _getWeatherForecastData.value = if (error is HttpException) {
            when (error.response().code()) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> Outcome.BadRequest(error.message ?: "")
                HttpURLConnection.HTTP_BAD_REQUEST -> Outcome.ApiError(error)
                //TODO socket time out exception msg should be "Timeout while fetching the data"
                else -> Outcome.Failure(error)
            }
        } else {
            Outcome.Failure(error)
        }
    }

}
