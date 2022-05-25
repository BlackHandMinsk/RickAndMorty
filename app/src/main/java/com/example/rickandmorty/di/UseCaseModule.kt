package com.example.rickandmorty.di


import com.example.rickandmorty.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRemoteListCharactersUseCase(
        repository: CharacterRepository
    ): GetRemoteListCharactersUseCase {
        return GetRemoteListCharactersUseCase(repository = repository)
    }

    @Provides
    fun provideGetRemoteCharacterUseCase(
        repository: CharacterRepository
    ): GetRemoteCharacterUseCase {
        return GetRemoteCharacterUseCase(repository = repository)
    }
}