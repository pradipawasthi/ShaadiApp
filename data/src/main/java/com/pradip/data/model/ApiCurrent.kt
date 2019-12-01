package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiCurrent(

    @SerializedName("temp_c") val tempC: Int = 0
)