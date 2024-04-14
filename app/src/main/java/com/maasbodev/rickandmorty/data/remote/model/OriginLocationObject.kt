package com.maasbodev.rickandmorty.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OriginLocationObject(
    val name: String,
    val url: String,
)
