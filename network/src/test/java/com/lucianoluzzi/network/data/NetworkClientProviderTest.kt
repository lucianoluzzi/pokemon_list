package com.lucianoluzzi.network.data

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Given network client provider")
class NetworkClientProviderTest {
    private val clientProvider = NetworkClientProvider()

    @Nested
    @DisplayName("When returns apollo client")
    inner class ApolloClient {

        @Test
        fun `then apollo client base url is as expected`() {
            val apolloClient = clientProvider.apolloClient
            assertThat(
                apolloClient.serverUrl.toUrl().toString()
            ).isEqualTo("https://graphql-pokemon.now.sh/")
        }
    }

    @Nested
    @DisplayName("When retrofit client")
    inner class RetrofitClient {

        @Test
        fun `then retrofit client base url is as expected`() {
            val expectedBaseUrl = "https://pokeapi.co/api/v2/"

            val retrofitClient = clientProvider.retrofit
            val actualBaseUrl = retrofitClient.baseUrl().toString()

            assertThat(actualBaseUrl).isEqualTo(expectedBaseUrl)
        }
    }
}