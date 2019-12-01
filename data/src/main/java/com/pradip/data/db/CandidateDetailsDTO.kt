package com.pradip.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "candidateDetails")
data class CandidateDetailsDTO(


    @PrimaryKey @ColumnInfo(name = "results")  val results: List<ResultsItemDTO> = listOf(
        ResultsItemDTO(
            "",
            "",
            "",
            DobDTO("", 0),
            NameDTO("", "", ""),
            LocationDTO("", "", "", ""),
            IdDTO("", ""),
            "",
            "",
            PictureDTO("", "", "")
        )
    ),
    @ColumnInfo(name = "page")  val page: Int

)