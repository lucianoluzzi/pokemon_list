package com.lucianoluzzi.network.di

import PokemonQuery
import PokemonsQuery
import com.lucianoluzzi.network.data.NetworkClientProvider
import org.koin.dsl.module

object NetworkModule {
    val module = module {
        single {
            NetworkClientProvider().apolloClient
        }

        single {
            NetworkClientProvider().retrofit
        }

        factory {
            PokemonQuery.builder()
        }
    }
}