package com.pradip.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CandidateDetailsDTO::class], version = 2, exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class ShaadiDatabase : RoomDatabase() {
    abstract fun candidateDetailsDao(): CandidateDetailsDao

    companion object {
        val DATABASE_NAME: String = "app_db"
    }
}