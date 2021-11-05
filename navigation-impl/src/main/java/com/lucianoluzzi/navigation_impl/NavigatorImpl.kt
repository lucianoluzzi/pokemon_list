package com.lucianoluzzi.navigation_impl

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lucianoluzzi.navigation.Navigator
import com.lucianoluzzi.pokemon_list.ui.fragment.PokemonListFragmentDirections

class NavigatorImpl : Navigator {

    override fun navigateToPokemonDetails(
        fragment: Fragment,
        pokemonName: String
    ) {
        if (pokemonName == "charizard") {
            fragment.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://shorturl.at/guBIT")
                )
            )
        } else {
            val navController = fragment.findNavController()
            val toPokemonDetails =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetails(pokemonName)
            navController.navigate(toPokemonDetails)
        }
    }
}