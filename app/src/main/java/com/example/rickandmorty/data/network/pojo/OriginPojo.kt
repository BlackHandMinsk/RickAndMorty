package com.example.rickandmorty.data.network.pojo


import com.squareup.moshi.Json

data class OriginPojo(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)