package com.maasbodev.rickandmorty.data.local

import androidx.annotation.VisibleForTesting
import com.maasbodev.rickandmorty.di.IO
import com.maasbodev.rickandmorty.domain.model.Character
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalCharacterDataSource @Inject constructor(
    appDataBase: AppDataBase,

    @IO private val dispatcher: CoroutineDispatcher,
) {

    @VisibleForTesting
    val characterDao = appDataBase.characterDao()

    suspend fun findById(id: Int): Character = withContext(dispatcher) {
        characterDao.findById(id).toCharacter()
    }
}
