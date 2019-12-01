package com.pradip.data.db

data class ResultsItemDTO(
    val nat: String = "",
    val gender: String = "",
    val phone: String = "",
    val dob: DobDTO,
    val name: NameDTO,
    val location: LocationDTO,
    val id: IdDTO,
    val cell: String = "",
    val email: String = "",
    val picture: PictureDTO
)