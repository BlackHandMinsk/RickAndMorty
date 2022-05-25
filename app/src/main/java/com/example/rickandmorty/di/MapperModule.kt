package com.example.rickandmorty.di


import com.example.rickandmorty.data.mappers.MapCharacterPojoToCharacterInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideMapCharacterPojoToCharacterInfo(): MapCharacterPojoToCharacterInfo {
        return MapCharacterPojoToCharacterInfo()
    }

}