package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiInfo(@SerializedName("seed") val seed: String = "",
                   @SerializedName("page")  val page: Int = 0,
                   @SerializedName("results")  val results: Int = 0,
                   @SerializedName("version")  val version: String = "")