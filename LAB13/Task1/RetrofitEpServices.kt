package com.example.rickandmorty

import retrofit2.Call
import retrofit2.http.*

interface RetrofitEpServices {
    @GET("episode/{epId}")
    fun getEpisodesList(@Path("epId") episodesKey: String): Call<List<Episode>>
}
