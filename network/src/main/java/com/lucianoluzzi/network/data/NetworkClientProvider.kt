package com.lucianoluzzi.network.data

import com.apollographql.apollo.ApolloClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClientProvider {

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val apolloClient: ApolloClient = ApolloClient.builder()
        .serverUrl(APOLLO_CLIENT_BASE_URL)
        .build()

    private companion object {
        private const val APOLLO_CLIENT_BASE_URL = "https://graphql-pokemon.now.sh/"
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}