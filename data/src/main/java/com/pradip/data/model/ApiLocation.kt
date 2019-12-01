package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiLocation(
    @SerializedName("country") val country: String = "",
    @SerializedName("city") val city: String = "",
    @SerializedName("postcode") val postcode: String = "",
    @SerializedName("state") val state: String = ""

)