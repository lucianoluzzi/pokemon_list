package com.lucianoluzzi.pokemon_list.di

import com.lucianoluzzi.pokemon_list.data.PokemonListRepositoryImpl
import com.lucianoluzzi.pokemon_list.data.api.GetPokemonsService
import com.lucianoluzzi.pokemon_list.domain.usecase.GetPokemonsUseCase
import com.lucianoluzzi.pokemon_list.domain.usecase.GetPokemonsUseCaseImpl
import com.lucianoluzzi.pokemon_list.ui.fragment.PokemonListFragment
import com.lucianoluzzi.pokemon_list.ui.viewmodel.PokemonListViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module
import retrofit2.Retrofit

object PokemonListModule {
    val module = module {
        factory<GetPokemonsUseCase> {
            val repository = PokemonListRepositoryImpl(
                service = (get() as Retrofit).create(GetPokemonsService::class.java)
            )
            GetPokemonsUseCaseImpl(repository)
        }

        fragment {
            val listViewModel = PokemonListViewModel(
                getPokemonsUseCase = get()
            )
            PokemonListFragment(
                viewModel = listViewModel,
                navigator = get()
            )
        }
    }
}