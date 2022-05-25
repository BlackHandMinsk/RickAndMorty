package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData

import com.example.rickandmorty.domain.model.CharacterInfo
import com.example.rickandmorty.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getListCharacters(): Flow<PagingData<CharacterInfo>>
    fun getCharacter(id: String):Flow<Resource<CharacterInfo>>
}
