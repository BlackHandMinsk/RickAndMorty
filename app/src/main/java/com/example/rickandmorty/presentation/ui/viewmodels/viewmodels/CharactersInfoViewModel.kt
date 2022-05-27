package com.example.rickandmorty.presentation.ui.viewmodels.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.CharacterInfo
import com.example.rickandmorty.domain.usecases.GetRemoteCharacterUseCase
import com.example.rickandmorty.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersInfoViewModel @Inject constructor(
    private val getRemoteCharacterUseCase: GetRemoteCharacterUseCase
) : ViewModel() {



    private var _characterInfo = MutableStateFlow<Resource<CharacterInfo>>(Resource.Empty)
    val characterInfo = _characterInfo.asStateFlow()

    fun getCharacter(id: String) {
        viewModelScope.launch {
            getRemoteCharacterUseCase.invoke(id).stateIn(viewModelScope).collect {
                _characterInfo.tryEmit(it)
            }
        }
    }


}