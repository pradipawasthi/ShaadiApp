package com.pradip.shaadi.dataModel

import com.pradip.data.model.ApiInfo
import com.pradip.data.model.ApiResultsItem

data class CandidateDetailsDataModel(val results: List<ResultsItemDataModel>?,
                                     val page : Int
)