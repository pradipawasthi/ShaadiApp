package com.pradip.domain.entities

data class LocationEntity(
    val localtime: String = "",
    val country: String = "",
    val localtimeEpoch: Int = 0,
    val name: String = "",
    val lon: Double = 0.0,
    val region: String = "",
    val lat: Double = 0.0
)