package com.maasbodev.rickandmorty.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginLocationObject,
    val location: OriginLocationObject,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)
