package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiForecast(@SerializedName("forecastday") val forecastday: List<ApiForecastdayItem>?)