package com.pradip.domain.entities

data class ResultsItemEntity(val nat: String = "",
                             val gender: String = "",
                             val phone: String = "",
                             val dob: DobEntity,
                             val name: NameEntity,
                             val location: LocationEntity,
                             val id: IdEntity,
                             val cell: String = "",
                             val email: String = "",
                             val picture: PictureEntity
)