package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.network.pojo.CharacterPojo
import com.example.rickandmorty.data.network.pojo.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

private const val PARAMS_PAGE = "page"

interface RickAndMortyApiService {

    @GET("/api/character")
    suspend fun getListCharacters(
        @retrofit2.http.Query(PARAMS_PAGE) page: Int
    ): Response<CharacterResponse?>

    @GET("/api/character/{id}")
    suspend fun getCharacter(
        @Path("id") id: String
    ): Response<CharacterPojo?>

}