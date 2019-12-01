package com.pradip.data.mapper


import com.pradip.data.model.*
import com.pradip.domain.entities.*
import javax.inject.Inject

class WeatherForecastMapper @Inject constructor() :
    BaseMapper<ApiWeatherForecast, WeatherForecastEntity> {

    override fun map(srcObject: ApiWeatherForecast) = with(srcObject) {
        WeatherForecastEntity(
            getLocation(location),
            getCurrentForeCast(current),
            getDailyForecast(forecast)

        )
    }

    private fun getDailyForecast(forecast: ApiForecast): ForecastEntity =
        forecast.run {
            ForecastEntity(
                getForecastByDay(forecast.forecastday)
            )
        }

    private fun getForecastByDay(forecastDay: List<ApiForecastdayItem>?): List<ForecastdayItemEntity>? =
        forecastDay?.map {
            ForecastdayItemEntity(
                it.date,
                it.dateEpoch,
                getForecastDayTemp(it.day)
            )
        }

    private fun getForecastDayTemp(day: ApiForecastdayItem.ApiDay): ForecastdayItemEntity.DayEntity =
        day.run {

            ForecastdayItemEntity.DayEntity(day.avgtempC)
        }

    private fun getCurrentForeCast(current: ApiCurrent): CurrentEntity = current.run {
        CurrentEntity(current.tempC)
    }

    private fun getLocation(location: ApiLocation): LocationEntity = location.run {
        LocationEntity(
            location.localtime,
            location.country,
            location.localtimeEpoch,
            location.name,
            location.lon,
            location.region,
            location.lat
        )

    }
}