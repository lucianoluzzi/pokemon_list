package com.lucianoluzzi.pokemon_list.ui.viewmodel

import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry

sealed class ListResponseState {
    data class Success(val value: List<PokemonEntry>) : ListResponseState()
    data class Error(val error: String? = null) : ListResponseState()
}