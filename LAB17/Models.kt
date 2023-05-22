package com.example.retrofitforecaster

import com.google.gson.annotations.SerializedName

data class Temperatures(
    val cod: String,
    val message: Double,
    val cnt: Long,
    val list: List<ListElement>,
    val city: City
)

data class City(
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)

data class ListElement(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,

    @SerializedName("dt_txt")
    val dtTxt: String
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Clouds(
    val all: Long
)

data class Main(
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double,

    val pressure: Double,

    @SerializedName("sea_level")
    val seaLevel: Long,

    @SerializedName("grnd_level")
    val grndLevel: Double,

    val humidity: Double,

    @SerializedName("temp_kf")
    val tempKf: Double
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)