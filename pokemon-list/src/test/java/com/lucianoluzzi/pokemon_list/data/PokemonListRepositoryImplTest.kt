package com.lucianoluzzi.pokemon_list.data

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.pokemon_list.data.api.GetPokemonsService
import com.lucianoluzzi.pokemon_list.data.responseModel.PokemonListResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Flowable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Given get pokemons service")
internal class PokemonListRepositoryImplTest {
    private val service = mock<GetPokemonsService>()
    private val repository = PokemonListRepositoryImpl(service)

    @Nested
    @DisplayName("When getPokemons called")
    inner class GetPokemons {

        @Test
        fun `then service called`() {
            repository.getPokemons()
            verify(service).getPokemons()
        }

        @Test
        fun `then service value returned`() {
            val pokemonListResponse = PokemonListResponse(
                count = 152,
                nextPage = "",
                previousPage = "",
                results = emptyList()
            )
            val expectedResponse = Flowable.just(pokemonListResponse)

            whenever(service.getPokemons()).thenReturn(expectedResponse)

            val actualResponse = repository.getPokemons()
            assertThat(expectedResponse).isEqualTo(actualResponse)
        }
    }
}