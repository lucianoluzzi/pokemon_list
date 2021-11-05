package com.lucianoluzzi.pokemon_list.data

import com.lucianoluzzi.pokemon_list.data.responseModel.PokemonListResponse
import io.reactivex.rxjava3.core.Flowable

interface PokemonListRepository {
    fun getPokemons(): Flowable<PokemonListResponse>
}
