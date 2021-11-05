package com.lucianoluzzi.pokemon_list.domain.usecase

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Given pokemon number")
internal class PokemonImageUrlBuilderTest {

    @Nested
    @DisplayName("When image url builder is instantiated")
    inner class Instance {

        @Test
        fun `then imageUrl follows pattern`() {
            val pokemonNumber = 145
            val expectedUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonNumber.png"

            val actualUrl = PokemonImageUrlBuilder(pokemonNumber).imageUrl

            assertThat(actualUrl).isEqualTo(expectedUrl)
        }
    }
}