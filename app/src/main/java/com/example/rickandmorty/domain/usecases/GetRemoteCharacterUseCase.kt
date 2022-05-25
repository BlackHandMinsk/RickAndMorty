package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.domain.repository.CharacterRepository


class GetRemoteCharacterUseCase(private val repository: CharacterRepository) {
    operator fun invoke(id: String) = repository.getCharacter(id)
}
