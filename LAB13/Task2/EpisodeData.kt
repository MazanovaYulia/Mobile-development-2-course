package com.example.rickandmorty

class EpisodeData : ArrayList<EpItem>()

data class EpItem(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
