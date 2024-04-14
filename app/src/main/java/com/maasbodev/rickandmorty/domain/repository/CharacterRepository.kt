package com.maasbodev.rickandmorty.domain.repository

import arrow.core.Either
import com.maasbodev.rickandmorty.data.local.LocalCharacterDataSource
import com.maasbodev.rickandmorty.domain.model.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val localCharacterDataSource: LocalCharacterDataSource,
) {

    suspend fun findById(id: Int): Either<Throwable, Character> {
        return Either.catch { localCharacterDataSource.findById(id) }
    }
}
