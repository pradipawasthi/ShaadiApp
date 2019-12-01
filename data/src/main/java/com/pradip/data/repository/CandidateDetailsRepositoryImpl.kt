package com.pradip.data.repository

import com.pradip.data.db.CandidateDetailsDao
import com.pradip.data.mapper.CandidateDetailsDTOMapper
import com.pradip.data.mapper.CandidateDetailsMapper
import com.pradip.data.service.CandidateDetailsService
import com.pradip.domain.entities.CandidateDetailsEntity
import com.pradip.domain.repository.CandidateDetailsRepository
import io.reactivex.Single
import javax.inject.Inject

class CandidateDetailsRepositoryImpl @Inject constructor(
    private val candidateDetailsService: CandidateDetailsService,
    private val candidateDetailsDTOMapper: CandidateDetailsDTOMapper,
    private val candidateDetailsMapper: CandidateDetailsMapper,
    private val candidateDetailsDao: CandidateDetailsDao

) : CandidateDetailsRepository {

    override fun getCandidateDetails(page: Int): Single<CandidateDetailsEntity> = candidateDetailsDao.getAllData().map{

        if(it.results.isEmpty()) insertDefaultListAndGet(page) else candidateDetailsDTOMapper.map(it)

    }

    private fun insertDefaultListAndGet(page: Int): CandidateDetailsEntity {

        candidateDetailsService.getCandidateDetails(page).map {
            candidateDetailsMapper.map(it)
        }.map {
            candidateDetailsDao.insertAll(it)
        }

        return candidateDetailsDao.getAllData()
    }


}