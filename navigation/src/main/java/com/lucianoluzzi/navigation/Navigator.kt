package com.lucianoluzzi.navigation

import androidx.fragment.app.Fragment

interface Navigator {
    fun navigateToPokemonDetails(fragment: Fragment, pokemonName: String)
}