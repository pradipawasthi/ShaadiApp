package com.pradip.data.mapper


import com.pradip.data.db.*
import com.pradip.data.model.*
import com.pradip.domain.entities.*
import javax.inject.Inject

class CandidateDetailsMapper @Inject constructor() :
    BaseMapper<ApiCandidateDetails, CandidateDetailsDTO> {

    override fun map(srcObject: ApiCandidateDetails) = with(srcObject) {
        CandidateDetailsDTO(
            getResults(results),
            info.page



        )
    }


    private fun getResults(resultsItems: List<ApiResultsItem>): List<ResultsItemDTO> =
        resultsItems?.map {
            ResultsItemDTO(
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

    private fun getDob(apiDob: ApiDob): DobDTO =
        apiDob.run {

            DobDTO(apiDob.date, apiDob.age)
        }

    private fun getName(apiName: ApiName): NameDTO =
        apiName.run {

            NameDTO(apiName.last, apiName.title, apiName.first)
        }

    private fun getId(apiId: ApiId): IdDTO =
        apiId.run {
            IdDTO(apiId.name, apiId.value)
        }


    private fun getLocation(location: ApiLocation): LocationDTO = location.run {
        LocationDTO(
            location.country,
            location.city,
            location.postcode,
            location.state
        )

    }


    private fun getPicture(apiPicture: ApiPicture): PictureDTO = apiPicture.run {
        PictureDTO(apiPicture.thumbnail, apiPicture.large, apiPicture.medium)
    }
}