package com.pradip.data.service

import com.pradip.data.model.ApiCandidateDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CandidateDetailsService {


    @GET("/results")
    fun getCandidateDetails(
        @Query("page") page: Int
    ): Single<ApiCandidateDetails>
}