package com.pradip.weatherapp.dataModel

data class WeatherForecastDataModel(
    val location: LocationDataModel,
    val current: CurrentDataModel,
    val forecast: ForecastDataModel
)