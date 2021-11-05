package com.lucianoluzzi.pokemon_list.data.api

import com.lucianoluzzi.pokemon_list.data.responseModel.PokemonListResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface GetPokemonsService {

    @GET("pokemon/?offset=0&limit=152")
    fun getPokemons(): Flowable<PokemonListResponse>
}