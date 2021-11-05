package com.lucianoluzzi.pokemon_list.data.responseModel

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val count: Int,
    @SerializedName("next") val nextPage: String?,
    @SerializedName("previous") val previousPage: String?,
    val results: List<PokemonEntryResponse>
)