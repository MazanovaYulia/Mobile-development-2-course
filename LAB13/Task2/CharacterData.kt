package com.example.rickandmorty

data class CharacterData(
    val info: Info,
    val results: List<Result>
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Any
)

data class Location(
    val name: String,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)

data class Result(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val created: String,
    val url: String
)


sealed class CharacterItem{
    data class CharacterInfo(
        val character: Result
    ) : CharacterItem()

    data class NextPage(
        val nextpageid: Int? = null
    ) : CharacterItem()
}
