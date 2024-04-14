package com.maasbodev.rickandmorty.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponseDto(
    val info: InfoDto,
    val results: List<CharacterDto>,
)
