package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiForecastdayItem(
    @SerializedName("date") val date: String = "",
    @SerializedName("date_epoch") val dateEpoch: Int = 0,
    @SerializedName("day") val day: ApiDay
) {

    data class ApiDay(@SerializedName("avgtemp_c") val avgtempC: Double = 0.0)
}


