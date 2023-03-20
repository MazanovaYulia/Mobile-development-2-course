package com.example.rickandmorty

import retrofit2.Call
import retrofit2.http.*

interface RetrofitCharServices {
    @GET("character/")
    fun getCharactersList(): Call<RecyclerItem.CharactersData>
}
