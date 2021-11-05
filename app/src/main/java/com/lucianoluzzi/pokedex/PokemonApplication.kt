package com.lucianoluzzi.pokedex

import android.app.Application
import com.lucianoluzzi.navigation_impl.di.NavigationImplModule
import com.lucianoluzzi.network.di.NetworkModule
import com.lucianoluzzi.pokemon_list.di.PokemonListModule
import com.lucianoluzzi.pokemons.di.PokemonModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin

@KoinExperimentalAPI
class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            fragmentFactory()
            val modules = listOf(
                NetworkModule.module,
                PokemonModule.module,
                PokemonListModule.module,
                NavigationImplModule.module
            )

            modules(modules)
        }
    }
}