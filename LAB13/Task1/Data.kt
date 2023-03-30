package com.example.rickandmorty

import java.io.Serializable

sealed class RecyclerItem : Serializable{
    data class CharactersData (
        val info: ButtonItem,
        val results: List<CharacterItem>
    ): RecyclerItem()

    data class CharacterItem (
        val id: Int = 0,
        val name: String = "",
        val status: String = "",
        val species: String = "",
        val type: String = "",
        val gender: String = "",
        val origin: Origin,
        val location: Location,
        val image: String = "",
        val episode: List<String>,
        val url: String = "",
        val created: String = ""
    ) : RecyclerItem()

    data class ButtonItem (
        val count: Int = 0,
        val pages: Int = 0,
        val next: String = "",
        val prev: String = ""
    ) : RecyclerItem()
}

data class Origin (
    val name: String = "",
    val url: String = ""
): Serializable

data class Location (
    val name: String = "",
    val url: String = ""
): Serializable

data class Episode (
    val id: Int = 0,
    val name: String = "",
    val air_date: String = "",
    val episode: String = "",
    val characters: List<String>,
    val url: String = "",
    val created: String = ""
)
