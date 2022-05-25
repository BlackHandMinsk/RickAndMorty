package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.domain.repository.CharacterRepository

class GetRemoteListCharactersUseCase(private val repository: CharacterRepository) {
    operator fun invoke() = repository.getListCharacters()
}
