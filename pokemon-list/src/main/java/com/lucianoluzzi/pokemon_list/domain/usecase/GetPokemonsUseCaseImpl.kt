package com.lucianoluzzi.pokemon_list.domain.usecase

import com.lucianoluzzi.network.domain.Response
import com.lucianoluzzi.pokemon_list.data.PokemonListRepository
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry
import io.reactivex.rxjava3.core.Flowable

class GetPokemonsUseCaseImpl(private val repository: PokemonListRepository) : GetPokemonsUseCase {

    override fun invoke(): Flowable<Response<List<PokemonEntry>>> {
        return repository.getPokemons().map<Response<List<PokemonEntry>>> { response ->
            val pokemonEntry = response.results.mapIndexed { index, pokemonEntryResponse ->
                val pokemonNumber = index + 1
                PokemonEntry(
                    number = index,
                    name = pokemonEntryResponse.name,
                    urlImage = PokemonImageUrlBuilder(pokemonNumber).imageUrl
                )
            }
            Response.Success(data = pokemonEntry)
        }.onErrorReturn {
            Response.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

    private companion object {
        const val DEFAULT_ERROR_MESSAGE = "Bzzzt! You must have a POKÉMON to use this!"
    }
}