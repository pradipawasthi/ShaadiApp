package com.pradip.domain.repository

import com.pradip.domain.entities.WeatherForecastEntity
import io.reactivex.Single

interface WeatherForecastRepository {

    fun getWeatherForecast(
        location: String,
        latitude: Double,
        longitude: Double,
        days: Int
    ): Single<WeatherForecastEntity>

}