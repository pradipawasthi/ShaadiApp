package com.pradip.data.repository

import com.pradip.data.mapper.WeatherForecastMapper
import com.pradip.data.service.WeatherService
import com.pradip.domain.entities.WeatherForecastEntity
import com.pradip.domain.repository.WeatherForecastRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherForecastMapper: WeatherForecastMapper
) : WeatherForecastRepository {

    override fun getWeatherForecast(
        location: String,
        latitude: Double,
        longitude: Double,
        days: Int
    ): Single<WeatherForecastEntity> =
        weatherService.getWeatherForecast(API_KEY, "Delhi", latitude, longitude, days).map {
            weatherForecastMapper.map(it)
        }

    companion object {
        const val API_KEY = "70ef3b7f24484a918b782502191207"
    }
}