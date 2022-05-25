package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.data.mappers.MapCharacterPojoToCharacterInfo
import com.example.rickandmorty.data.network.RickAndMortyApiService
import com.example.rickandmorty.data.network.repository.CharacterRepositoryImpl
import com.example.rickandmorty.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(
        apiService: RickAndMortyApiService,
        mapCharacterPojoToCharacterInfo: MapCharacterPojoToCharacterInfo,
        @ApplicationContext context: Context
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            service = apiService,
            mapCharacterPojoToCharacterInfo = mapCharacterPojoToCharacterInfo,
            context=context
        )
    }
}