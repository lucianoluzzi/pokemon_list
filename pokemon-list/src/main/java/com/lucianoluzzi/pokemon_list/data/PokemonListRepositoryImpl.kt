package com.lucianoluzzi.pokemon_list.data

import com.lucianoluzzi.pokemon_list.data.api.GetPokemonsService
import com.lucianoluzzi.pokemon_list.data.responseModel.PokemonListResponse
import io.reactivex.rxjava3.core.Flowable

class PokemonListRepositoryImpl(
    private val service: GetPokemonsService
) : PokemonListRepository {

    override fun getPokemons(): Flowable<PokemonListResponse> = service.getPokemons()
}