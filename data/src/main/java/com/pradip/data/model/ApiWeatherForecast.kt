package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiWeatherForecast(
    @SerializedName("location") val location: ApiLocation,
    @SerializedName("current") val current: ApiCurrent,
    @SerializedName("forecast") val forecast: ApiForecast
)