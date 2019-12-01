package com.pradip.shaadi.mapper

import com.pradip.data.mapper.BaseMapper
import com.pradip.domain.entities.*
import com.pradip.shaadi.dataModel.*
import javax.inject.Inject

/**
 * Created by Pradip Awasthi on 2019-09-14.
 */

class CandidateDetailsDataMapper @Inject constructor() :
    BaseMapper<CandidateDetailsEntity, CandidateDetailsDataModel> {

    override fun map(srcObject: CandidateDetailsEntity) = with(srcObject) {
        CandidateDetailsDataModel(
            getResults(results),
            page

        )
    }

    private fun getInfo(apiInfo: InfoEntity): InfoDataModel =
        apiInfo.run {
            InfoDataModel(
                apiInfo.seed,
                apiInfo.page,
                apiInfo.results,
                apiInfo.version
            )
        }

    private fun getResults(resultsItems: List<ResultsItemEntity>?): List<ResultsItemDataModel>? =
        resultsItems?.map {
            ResultsItemDataModel(
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

    private fun getDob(dobEntity: DobEntity): DobDataModel =
        dobEntity.run {

            DobDataModel(dobEntity.date, dobEntity.age)
        }

    private fun getName(nameEntity: NameEntity): NameDataModel =
        nameEntity.run {

            NameDataModel(nameEntity.last, nameEntity.title, nameEntity.first)
        }

    private fun getId(idEntity: IdEntity): IdDataModel =
        idEntity.run {
            IdDataModel(idEntity.name, idEntity.value)
        }


    private fun getLocation(location: LocationEntity): LocationDataModel = location.run {
        LocationDataModel(
            location.country,
            location.city,
            location.postcode,
            location.state
        )

    }



    private fun getPicture(pictureEntity: PictureEntity): PictureDataModel = pictureEntity.run {
        PictureDataModel(pictureEntity.thumbnail, pictureEntity.large, pictureEntity.medium)
    }

}