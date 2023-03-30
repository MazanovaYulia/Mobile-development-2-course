package com.example.rickandmorty.retrofitpkg

import com.example.rickandmorty.CharacterData
import com.example.rickandmorty.EpisodeData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://rickandmortyapi.com/api/"

interface RetrofitServices {
    @GET("character?")
    fun getCharactersList(@Query("page") pageId: Int): Call<CharacterData>

    @GET("episode/{id}")
    fun getEpisodeList(@Path("id") epId: ArrayList<Int>): Call<EpisodeData>

    companion object {
        fun createApiService(): RetrofitServices {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RetrofitServices::class.java)
        }
    }
}
