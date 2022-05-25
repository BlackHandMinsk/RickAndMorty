package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.pojo.CharacterPojo
import com.example.rickandmorty.domain.model.CharacterInfo
import com.example.rickandmorty.domain.util.Mapper


class MapCharacterPojoToCharacterInfo : Mapper<CharacterPojo, CharacterInfo> {
    override fun map(from: CharacterPojo): CharacterInfo {
        return CharacterInfo(
            id = from.id,
            name = from.name,
            status = from.status,
            species = from.species,
            type = from.type,
            gender = from.gender,
            origin = from.origin.name,
            location = from.location.name,
            image = from.image,
            episode = from.episode,
            url = from.url
        )
    }
}
