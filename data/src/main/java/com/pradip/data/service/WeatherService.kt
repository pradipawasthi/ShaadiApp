package com.pradip.data.service

import com.pradip.data.model.ApiWeatherForecast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("v1/forecast.json")
    fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("lat") latitude: Double,
        @Query("long") longitude: Double,
        @Query("days") days: Int
    ): Single<ApiWeatherForecast>
}