package com.example.retrofitforecaster

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Coordinate_data")
    data class Coordinate (
        @PrimaryKey
        val x:String = "",
        val y:String = ""
    )

