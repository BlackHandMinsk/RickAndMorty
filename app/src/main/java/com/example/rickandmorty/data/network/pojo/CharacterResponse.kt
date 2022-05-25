package com.example.rickandmorty.data.network.pojo


import com.example.rickandmorty.data.network.pojo.CharacterPojo
import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "results") val results: List<CharacterPojo> = emptyList()
)