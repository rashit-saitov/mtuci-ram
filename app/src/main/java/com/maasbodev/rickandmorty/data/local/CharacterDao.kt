package com.maasbodev.rickandmorty.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CharacterDao {

    @Upsert
    suspend fun upsertAll(characters: List<CharacterDbModel>)

    @Query("SELECT * FROM CharacterDbModel")
    fun pagingSource(): PagingSource<Int, CharacterDbModel>

    @Query("SELECT * FROM CharacterDbModel WHERE id = :id")
    fun findById(id: Int): CharacterDbModel

    @Query("DELETE FROM CharacterDbModel")
    suspend fun clearAll()
}
