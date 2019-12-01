package com.pradip.data.mapper


import com.pradip.data.db.*
import com.pradip.domain.entities.*
import javax.inject.Inject

class CandidateDetailsDTOMapper @Inject constructor() :
    BaseMapper<CandidateDetailsDTO, CandidateDetailsEntity> {

    override fun map(srcObject: CandidateDetailsDTO) = with(srcObject) {
        CandidateDetailsEntity(
            getResults(results),
            page



        )
    }


    private fun getResults(resultsItems: List<ResultsItemDTO>?): List<ResultsItemEntity>? =
        resultsItems?.map {
            ResultsItemEntity(
                it.nat,
                it.gender,
                it.phone,
                getDob(it.dob),
                getName(it.name),
                getLocation(it.location),
                getId(it.id),
                it.cell,
                it.email,
                getPicture(it.picture)
            )
        }

    private fun getDob(apiDob: DobDTO): DobEntity =
        apiDob.run {

            DobEntity(apiDob.date, apiDob.age)
        }

    private fun getName(apiName: NameDTO): NameEntity =
        apiName.run {

            NameEntity(apiName.last, apiName.title, apiName.first)
        }

    private fun getId(apiId: IdDTO): IdEntity =
        apiId.run {
            IdEntity(apiId.name, apiId.value)
        }


    private fun getLocation(location: LocationDTO): LocationEntity = location.run {
        LocationEntity(
            location.country,
            location.city,
            location.postcode,
            location.state
        )

    }


    private fun getPicture(apiPicture: PictureDTO): PictureEntity = apiPicture.run {
        PictureEntity(apiPicture.thumbnail, apiPicture.large, apiPicture.medium)
    }
}