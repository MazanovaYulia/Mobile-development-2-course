package com.example.retrofitforecaster

import androidx.room.*

@Dao
interface DAO {
    @Query("SELECT * FROM Weather")
    suspend fun getAll(): List<Weather>

    @Insert
    suspend fun insertAll(vararg weather: Weather)

    @Query("DELETE FROM Weather")
    suspend fun deleteAll()
}
