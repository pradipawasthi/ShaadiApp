package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiDob(@SerializedName("date") val date: String = "",
                  @SerializedName("age")  val age: Int = 0)