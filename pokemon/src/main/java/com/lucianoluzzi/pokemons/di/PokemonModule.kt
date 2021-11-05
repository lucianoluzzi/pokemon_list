package com.lucianoluzzi.pokemons.di

import PokemonQuery
import com.apollographql.apollo.ApolloClient
import com.lucianoluzzi.pokemons.details.data.PokemonsDetailsRepositoryImpl
import com.lucianoluzzi.pokemons.details.domain.usecase.GetPokemonDetailsUseCase
import com.lucianoluzzi.pokemons.details.domain.usecase.GetPokemonDetailsUseCaseImpl
import com.lucianoluzzi.pokemons.details.ui.fragment.PokemonDetailsFragment
import com.lucianoluzzi.pokemons.details.ui.viewmodel.PokemonDetailsViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

object PokemonModule {
    val module = module {
        factory<GetPokemonDetailsUseCase> {
            val repository = PokemonsDetailsRepositoryImpl(
                get() as ApolloClient,
                get() as PokemonQuery.Builder
            )
            GetPokemonDetailsUseCaseImpl(
                repository
            )
        }

        fragment {
            val detailsViewModel = PokemonDetailsViewModel(get() as GetPokemonDetailsUseCase)
            PokemonDetailsFragment(detailsViewModel)
        }
    }
}