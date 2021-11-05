package com.lucianoluzzi.pokemon_list.domain.usecase

class PokemonImageUrlBuilder(pokemonNumber: Int) {
    val imageUrl = URL_IMAGE_PATH.replace(
        POKEMON_NUMBER_PLACEHOLDER,
        pokemonNumber.toString()
    )

    private companion object {
        private const val POKEMON_NUMBER_PLACEHOLDER = "[POKEMON_NUMBER]"
        private const val URL_IMAGE_PATH =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$POKEMON_NUMBER_PLACEHOLDER.png"
    }
}