package com.doubtnutapp.domain.similarVideo.interactor

import com.pradip.domain.entities.CandidateDetailsEntity
import com.pradip.domain.interactor.SingleUseCase
import com.pradip.domain.repository.CandidateDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCandidateDetailsUseCase @Inject constructor(private val candidateDetailsRepository: CandidateDetailsRepository) :
    SingleUseCase<CandidateDetailsEntity, GetCandidateDetailsUseCase.Param> {

    override fun execute(param: Param): Single<CandidateDetailsEntity> =
        candidateDetailsRepository.getCandidateDetails(

            param.page
        )


    data class Param(
        val page: Int
    )
}

