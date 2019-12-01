package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiName(@SerializedName("last")val last: String = "",
                   @SerializedName("title") val title: String = "",
                   @SerializedName("first") val first: String = "")