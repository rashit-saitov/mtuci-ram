package com.maasbodev.rickandmorty.data.remote

import com.maasbodev.rickandmorty.data.remote.model.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): CharacterResponseDto

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}
