package com.maasbodev.rickandmorty.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)
