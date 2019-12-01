package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiPicture(@SerializedName("thumbnail") val thumbnail: String = "",
                      @SerializedName("large") val large: String = "",
                      @SerializedName("medium") val medium: String = "")