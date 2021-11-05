package com.lucianoluzzi.pokemon_list.domain.usecase

import com.lucianoluzzi.network.domain.Response
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry
import io.reactivex.rxjava3.core.Flowable

interface GetPokemonsUseCase {
    fun invoke(): Flowable<Response<List<PokemonEntry>>>
}