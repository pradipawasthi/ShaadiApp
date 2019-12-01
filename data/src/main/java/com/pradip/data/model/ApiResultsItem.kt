package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiResultsItem(
    @SerializedName("nat") val nat: String = "",
    @SerializedName("gender") val gender: String = "",
    @SerializedName("phone") val phone: String = "",
    @SerializedName("dob") val dob: ApiDob,
    @SerializedName("name") val name: ApiName,
    @SerializedName("location") val location: ApiLocation,
    @SerializedName("id") val id: ApiId,
    @SerializedName("cell") val cell: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("picture") val picture: ApiPicture
)