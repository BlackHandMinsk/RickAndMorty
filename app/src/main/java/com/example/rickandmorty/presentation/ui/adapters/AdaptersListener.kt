package com.example.rickandmorty.presentation.ui.adapters

import com.example.rickandmorty.domain.model.CharacterInfo


interface AdaptersListener {
    fun onClickItem(characterInfo: CharacterInfo)
}
