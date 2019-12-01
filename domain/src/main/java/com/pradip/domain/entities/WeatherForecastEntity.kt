package com.pradip.domain.entities

data class WeatherForecastEntity(
    val location: LocationEntity,
    val current: CurrentEntity,
    val forecast: ForecastEntity
)