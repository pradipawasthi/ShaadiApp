package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiId(@SerializedName("name") val name: String = "",
                 @SerializedName("value")  val value: String? = "")