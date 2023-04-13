package com.example.retrofitforecaster

import androidx.room.*

@Entity
data class Weather(
    val temp: Double = 0.0,
    @PrimaryKey val dt_txt: String = "",
    val description: String = ""
)

data class DataWeather(
    val cod: String = "",
    val message: Int = 0,
    val cnt: Int = 0,
    val list: List<ListItem>,
    val city: City
)

data class ListItem(
    val dt: String = "",
    val main: Main,
    val weather: List<WeatherItem>?,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: String = "",
    val pop: String = "",
    val sys: Sys,
    val dt_txt: String = ""
)

data class City(
    val country: String = "",
    val coord: Coord,
    val sunrise: Int = 0,
    val timezone: Int = 0,
    val sunset: Int = 0,
    val name: String = "",
    val id: Int = 0,
    val population: Int = 0
)

data class Main(
    val temp: Double = 0.0,
    val tempMin: Double = 0.0,
    val grndLevel: Int = 0,
    val tempKf: Double = 0.0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val seaLevel: Int = 0,
    val feelsLike: Double = 0.0,
    val tempMax: Double = 0.0
)

data class WeatherItem(
    val icon: String = "",
    val description: String = "",
    val main: String = "",
    val id: Int = 0
)

data class Coord(
    val lon: Double = 0.0,
    val lat: Double = 0.0
)

data class Clouds(val all: Int = 0)

data class Sys(val pod: String = "")

data class Wind(
    val deg: Int = 0,
    val speed: Double = 0.0,
    val gust: Double = 0.0
)
