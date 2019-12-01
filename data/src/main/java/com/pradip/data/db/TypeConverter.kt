package com.pradip.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object TypeConverter {

    private val gson = Gson()


    @TypeConverter
    @JvmStatic
    fun fromResultsItemDTO(genres: List<ResultsItemDTO>): String {
        return gson.toJson(genres)
    }

    @TypeConverter
    @JvmStatic
    fun toResultsItemDTO(genres: String?): List<ResultsItemDTO>? {
        if (genres == null) {
            return emptyList()
        }

        val listType = object : TypeToken<List<ResultsItemDTO>>() {

        }.type

        return gson.fromJson(genres, listType)
    }



    @TypeConverter
    @JvmStatic
    fun toDobDTO(value: String?): DobDTO? {

        if (value == null) {
            return null
        }

        val listType = object : TypeToken<DobDTO>() {

        }.type

        return gson.fromJson(value, listType)

    }

    @TypeConverter
    @JvmStatic
    fun fromDobDTO(value: DobDTO?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toNameDTO(value: String?): NameDTO? {
        return gson.fromJson(value, object : TypeToken<NameDTO>() {
        }.type)
    }

    @TypeConverter
    @JvmStatic
    fun fromNameDTO(value: NameDTO?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toLocationDTO(value: String?): LocationDTO? {
        return gson.fromJson(value, object : TypeToken<LocationDTO>() {
        }.type)
    }

    @TypeConverter
    @JvmStatic
    fun fromLocationDTO(value: LocationDTO?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toIdDTO(value: String?): IdDTO? {
        return gson.fromJson(value, object : TypeToken<IdDTO>() {
        }.type)
    }

    @TypeConverter
    @JvmStatic
    fun fromIdDTO(value: IdDTO?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toPictureDTO(value: String?): PictureDTO? {
        return gson.fromJson(value, object : TypeToken<PictureDTO>() {
        }.type)
    }

    @TypeConverter
    @JvmStatic
    fun fromPictureDTO(value: PictureDTO?): String? {
        return gson.toJson(value)
    }


    @JvmStatic
    fun toInfoDTO(value: String?): InfoDTO? {
        if (value == null) {
            return null
        }

        val listType = object : TypeToken<List<InfoDTO>>() {

        }.type

        return gson.fromJson(value, listType)

    }

    @TypeConverter
    @JvmStatic
    fun fromInfoDTO(value: InfoDTO?): String? {
        return gson.toJson(value)
    }
}