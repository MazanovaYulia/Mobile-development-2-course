package com.example.retrofitforecaster

import androidx.room.*


@Dao
interface CordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(cord: Coordinate)

    @Query("SELECT * FROM Coordinate_data")
    fun getAll(): List<Coordinate>

    @Query("SELECT x FROM Coordinate_data")
    fun getX(): String

    @Query("SELECT y FROM Coordinate_data")
    fun getY(): String

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(cord: Coordinate)

}



