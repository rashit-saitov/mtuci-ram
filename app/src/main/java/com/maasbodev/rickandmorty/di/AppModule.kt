package com.maasbodev.rickandmorty.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.maasbodev.rickandmorty.data.local.AppDataBase
import com.maasbodev.rickandmorty.data.local.CharacterDbModel
import com.maasbodev.rickandmorty.data.remote.CharacterApiService
import com.maasbodev.rickandmorty.data.remote.RemoteCharacterDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "rickmorty-db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterApiService(): CharacterApiService {
        return Retrofit.Builder()
            .baseUrl(CharacterApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCharacterPager(
        characterDb: AppDataBase,
        characterApiService: CharacterApiService
    ): Pager<Int, CharacterDbModel> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = RemoteCharacterDataSource(
                appDataBase = characterDb,
                characterApiService = characterApiService,
            ),
            pagingSourceFactory = {
                characterDb.characterDao().pagingSource()
            }
        )
    }
}
