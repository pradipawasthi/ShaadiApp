package com.doubtnutapp.domain.similarVideo.interactor

import com.pradip.domain.entities.WeatherForecastEntity
import com.pradip.domain.interactor.SingleUseCase
import com.pradip.domain.repository.WeatherForecastRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(private val weatherForecastRepository: WeatherForecastRepository) :
    SingleUseCase<WeatherForecastEntity, GetWeatherForecastUseCase.Param> {

    override fun execute(param: Param): Single<WeatherForecastEntity> =
        weatherForecastRepository.getWeatherForecast(
            param.location,
            param.latitude,
            param.longitude,
            param.days
        )


    data class Param(
        val location: String,
        val latitude: Double,
        val longitude: Double,
        val days: Int
    )
}

