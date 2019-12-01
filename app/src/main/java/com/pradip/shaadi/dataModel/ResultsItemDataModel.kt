package com.pradip.shaadi.dataModel

import com.pradip.data.model.*

data class ResultsItemDataModel(val nat: String = "",
                                val gender: String = "",
                                val phone: String = "",
                                val dob: DobDataModel,
                                val name: NameDataModel,
                                val location: LocationDataModel,
                                val id: IdDataModel,
                                val cell: String = "",
                                val email: String = "",
                                val picture: PictureDataModel
)