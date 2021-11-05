package com.lucianoluzzi.pokemon_list.ui.adapter

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry
import org.junit.jupiter.api.Test

class PokemonEntryDiffCallbackTest {
    private val diffCallback = PokemonEntryDiffCallback()

    @Test
    fun `assert not the same when different numbers`() {
        val firstPokemon = PokemonEntry(1, "pikachu", "www")
        val secondPokemon = PokemonEntry(2, "pikachu", "www")

        assertThat(diffCallback.areItemsTheSame(firstPokemon, secondPokemon)).isFalse()
    }

    @Test
    fun `assert same when same numbers`() {
        val firstPokemon = PokemonEntry(1, "pikachu", "www")
        val secondPokemon = PokemonEntry(1, "pikachu", "www")

        assertThat(diffCallback.areItemsTheSame(firstPokemon, secondPokemon)).isTrue()
    }

    @Test
    fun `assert contents are the same when same`() {
        val firstPokemon = PokemonEntry(1, "pikachu", "www")
        val secondPokemon = PokemonEntry(1, "pikachu", "www")

        assertThat(diffCallback.areContentsTheSame(firstPokemon, secondPokemon)).isTrue()
    }

    @Test
    fun `assert contents not the same when different number`() {
        val firstPokemon = PokemonEntry(1, "pikachu", "www")
        val secondPokemon = PokemonEntry(2, "pikachu", "www")

        assertThat(diffCallback.areContentsTheSame(firstPokemon, secondPokemon)).isFalse()
    }

    @Test
    fun `assert contents not the same when different name`() {
        val firstPokemon = PokemonEntry(1, "pikachu", "www")
        val secondPokemon = PokemonEntry(2, "bulbasaur", "www")

        assertThat(diffCallback.areContentsTheSame(firstPokemon, secondPokemon)).isFalse()
    }

    @Test
    fun `assert contents not the same when different image`() {
        val firstPokemon = PokemonEntry(1, "pikachu", "www")
        val secondPokemon = PokemonEntry(1, "pikachu", "ww")

        assertThat(diffCallback.areContentsTheSame(firstPokemon, secondPokemon)).isFalse()
    }
}