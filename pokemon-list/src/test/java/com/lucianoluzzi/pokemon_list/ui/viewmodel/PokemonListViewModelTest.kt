package com.lucianoluzzi.pokemon_list.ui.viewmodel

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.network.domain.Response
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry
import com.lucianoluzzi.pokemon_list.domain.usecase.GetPokemonsUseCase
import com.lucianoluzzi.tests.extensions.InstantExecutorExtension
import com.lucianoluzzi.tests.extensions.OverrideSchedulersExtension
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Flowable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("Given get pokemons use case")
@ExtendWith(InstantExecutorExtension::class, OverrideSchedulersExtension::class)
class PokemonListViewModelTest {
    private val useCase = mock<GetPokemonsUseCase>()
    private lateinit var viewModel: PokemonListViewModel

    @Nested
    @DisplayName("when returns Response Error")
    inner class Error {

        @Test
        fun `then UI state error is emitted`() {
            val expectedErrorMessage = "error!"
            whenever(useCase.invoke()).thenReturn(
                Flowable.just(Response.Error(expectedErrorMessage))
            )

            viewModel = PokemonListViewModel(useCase)

            val pokemonsLiveData = viewModel.pokemons
            assertThat(pokemonsLiveData.value is ListResponseState.Error).isTrue()
            val errorResponse = pokemonsLiveData.value as ListResponseState.Error
            assertThat(errorResponse.error).isEqualTo(expectedErrorMessage)
        }
    }

    @Nested
    @DisplayName("when returns Success")
    inner class Success {

        @Test
        fun `then UI state success is emitted`() {
            val expectedPokemonList = listOf(
                PokemonEntry(0, "bulbasaur", "urlImage"),
                PokemonEntry(0, "bulbasaur", "urlImage"),
                PokemonEntry(0, "bulbasaur", "urlImage")
            )
            val responseSuccess = Response.Success(
                data = expectedPokemonList
            )

            whenever(useCase.invoke()).thenReturn(
                Flowable.just(responseSuccess)
            )
            viewModel = PokemonListViewModel(useCase)

            val pokemonsLiveData = viewModel.pokemons.value
            assertThat(pokemonsLiveData is ListResponseState.Success).isTrue()
            val successResponse = pokemonsLiveData as ListResponseState.Success
            assertThat(successResponse.value).isEqualTo(expectedPokemonList)
        }
    }
}