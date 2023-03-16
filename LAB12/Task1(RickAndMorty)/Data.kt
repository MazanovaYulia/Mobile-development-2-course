package com.example.rickandmorty

import java.io.Serializable

data class CharactersData (
    val info: Info,
    val results: List<Character>
): Serializable

data class Info (
    val count: Int = 0,
    val pages: Int = 0,
    val next: String = "",
    val prev: String = ""
): Serializable

data class Character (
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
): Serializable

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
