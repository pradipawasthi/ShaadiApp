package com.pradip.weatherapp.mapper

import com.pradip.data.mapper.BaseMapper
import com.pradip.domain.entities.*
import com.pradip.weatherapp.dataModel.*
import javax.inject.Inject

/**
 * Created by Pradip Awasthi on 2019-09-14.
 */

class WeatherForecastDataMapper @Inject constructor() :
    BaseMapper<WeatherForecastEntity, WeatherForecastDataModel> {

    override fun map(srcObject: WeatherForecastEntity) = with(srcObject) {
        WeatherForecastDataModel(
            getLocation(location),
            getCurrentForeCast(current),
            getDailyForecast(forecast)

        )
    }

    private fun getDailyForecast(forecast: ForecastEntity): ForecastDataModel =
        forecast.run {
            ForecastDataModel(
                getForecastByDay(forecast.forecastday)
            )
        }

    private fun getForecastByDay(forecastDay: List<ForecastdayItemEntity>?): List<ForecastdayDataModel>? =
        forecastDay?.map {
            ForecastdayDataModel(
                it.date,
                it.dateEpoch,
                getForecastDayTemp(it.day)
            )
        }

    private fun getForecastDayTemp(day: ForecastdayItemEntity.DayEntity): ForecastdayDataModel.DayDataModel =
        day.run {
            ForecastdayDataModel.DayDataModel(day.avgtempC)
        }

    private fun getCurrentForeCast(current: CurrentEntity): CurrentDataModel = current.run {
        CurrentDataModel(current.tempC)
    }

    private fun getLocation(location: LocationEntity): LocationDataModel = location.run {
        LocationDataModel(
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