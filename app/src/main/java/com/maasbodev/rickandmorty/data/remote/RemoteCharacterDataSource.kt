package com.maasbodev.rickandmorty.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.maasbodev.rickandmorty.data.local.AppDataBase
import com.maasbodev.rickandmorty.data.local.CharacterDbModel
import com.maasbodev.rickandmorty.data.local.toCharacterDbModel
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class RemoteCharacterDataSource @Inject constructor(
    private val appDataBase: AppDataBase,
    private val characterApiService: CharacterApiService,
) : RemoteMediator<Int, CharacterDbModel>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterDbModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val characterResults = characterApiService.getCharacters(
                page = loadKey,
            )
            val characters = characterResults.results

            appDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDataBase.characterDao().clearAll()
                }
                val characterDbModels = characters.map { it.toCharacterDbModel() }
                appDataBase.characterDao().upsertAll(characterDbModels)
            }

            MediatorResult.Success(
                endOfPaginationReached = characters.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
