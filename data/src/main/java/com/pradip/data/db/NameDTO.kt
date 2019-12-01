package com.pradip.data.db

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class NameDTO(val last: String = "",
                    val title: String = "",
                    val first: String = "")