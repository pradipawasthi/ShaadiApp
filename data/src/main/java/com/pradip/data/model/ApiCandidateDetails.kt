package com.pradip.data.model

import com.google.gson.annotations.SerializedName

data class ApiCandidateDetails(
    @SerializedName("results") val results: List<ApiResultsItem>,
    @SerializedName("info") val info: ApiInfo
)