package com.pradip.data.db

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class DobDTO(@SerializedName("date") val date: String = "",
                   @SerializedName("age")  val age: Int = 0)