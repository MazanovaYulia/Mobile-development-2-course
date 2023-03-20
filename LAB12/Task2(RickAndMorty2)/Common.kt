package com.example.rickandmorty

object Common {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    val retrofitCharService: RetrofitCharServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitCharServices::class.java)
}
