package com.example.retrofitforecaster

import okhttp3.OkHttpClient

object Common {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    fun getRetrofitServices(client: OkHttpClient): RetrofitServices {
        return RetrofitClient.getClient(BASE_URL, client).create(RetrofitServices::class.java)
    }

}
