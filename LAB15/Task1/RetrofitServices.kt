package com.example.retrofitforecaster_lab10

import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("forecast?q=Shklov&appid=9bd98d0696500560ef3206451b125490&units=metric&lang=ru")
    fun getWeatherList(): Call<DataWeather>
}