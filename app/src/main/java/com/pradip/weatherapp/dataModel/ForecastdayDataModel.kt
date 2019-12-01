package com.pradip.weatherapp.dataModel

data class ForecastdayDataModel(
    val date: String = "",
    val dateEpoch: Int = 0,
    val day: DayDataModel
) {

    data class DayDataModel(val avgtempC: Double = 0.0)
}
