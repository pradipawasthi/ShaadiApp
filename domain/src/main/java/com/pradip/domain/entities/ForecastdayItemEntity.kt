package com.pradip.domain.entities

data class ForecastdayItemEntity(
    val date: String = "",
    val dateEpoch: Int = 0,
    val day: DayEntity
) {

    data class DayEntity(val avgtempC: Double = 0.0)
}
