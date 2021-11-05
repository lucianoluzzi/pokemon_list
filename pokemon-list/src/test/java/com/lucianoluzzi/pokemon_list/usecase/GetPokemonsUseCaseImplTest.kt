package com.lucianoluzzi.pokemon_list.usecase

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.network.domain.Response
import com.lucianoluzzi.pokemon_list.data.PokemonListRepository
import com.lucianoluzzi.pokemon_list.data.responseModel.PokemonEntryResponse
import com.lucianoluzzi.pokemon_list.data.responseModel.PokemonListResponse
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry
import com.lucianoluzzi.pokemon_list.domain.usecase.GetPokemonsUseCaseImpl
import com.lucianoluzzi.tests.extensions.OverrideSchedulersExtension
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Flowable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(OverrideSchedulersExtension::class)
@DisplayName("Given repository")
class GetPokemonsUseCaseImplTest {
    private val repository = mock<PokemonListRepository>()
    private val useCase = GetPokemonsUseCaseImpl(repository)

    @Nested
    @DisplayName("When repository throws")
    inner class Error {

        @Test
        fun `then ResultError is returned`() {
            whenever(repository.getPokemons()).thenReturn(
                Flowable.error(Exception())
            )

            val response = useCase.invoke().blockingFirst()

            assertThat(response is Response.Error).isTrue()
        }
    }

    @Nested
    @DisplayName("When repository returns PokemonListResponse")
    inner class Success {

        @Test
        fun `then use case returns Response Success`() {
            val pokemonListResponse = PokemonListResponse(
                count = 100,
                nextPage = "nextPage",
                previousPage = "previousPage",
                results = emptyList()
            )

            whenever(repository.getPokemons()).thenReturn(
                Flowable.just(pokemonListResponse)
            )
            val response = useCase.invoke().blockingFirst()

            assertThat(response is Response.Success).isTrue()
        }

        @Test
        fun `then use case map data to PokemonEntry`() {
            val expectedResponse = PokemonListResponse(
                count = 100,
                nextPage = "nextPage",
                previousPage = "previousPage",
                results = listOf(
                    PokemonEntryResponse(
                        name = "bulbasaur",
                        entryUrl = "http://test-url.com"
                    ),
                    PokemonEntryResponse(
                        name = "ivysaur",
                        entryUrl = "http://test-url.com"
                    ),
                    PokemonEntryResponse(
                        name = "venusaur",
                        entryUrl = "http://test-url.com"
                    )
                )
            )
            val expectedList = listOf(
                PokemonEntry(
                    number = 0,
                    name = "bulbasaur",
                    urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                ),
                PokemonEntry(
                    number = 1,
                    name = "ivysaur",
                    urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
                ),
                PokemonEntry(
                    number = 2,
                    name = "venusaur",
                    urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
                )
            )

            whenever(repository.getPokemons()).thenReturn(
                Flowable.just(expectedResponse)
            )
            val actualResponse = useCase.invoke().blockingFirst()

            assertThat(actualResponse is Response.Success).isTrue()
            val successResponse = actualResponse as Response.Success
            assertThat(successResponse.data).isEqualTo(expectedList)
        }
    }
}