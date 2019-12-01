package com.pradip.domain.repository

import com.pradip.domain.entities.CandidateDetailsEntity
import io.reactivex.Single

interface CandidateDetailsRepository {

    fun getCandidateDetails(
        page: Int
    ): Single<CandidateDetailsEntity>

}