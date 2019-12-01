package com.pradip.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
abstract class CandidateDetailsDao {
    @Query("SELECT * FROM candidateDetails")
    abstract fun getAllData(): Single<CandidateDetailsDTO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAll(vararg candidateDetailsDTO: CandidateDetailsDTO)

}