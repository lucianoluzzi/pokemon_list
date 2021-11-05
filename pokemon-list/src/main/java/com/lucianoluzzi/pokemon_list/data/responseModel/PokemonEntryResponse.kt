package com.lucianoluzzi.pokemon_list.data.responseModel

import com.google.gson.annotations.SerializedName

data class PokemonEntryResponse(
    val name: String,
    @SerializedName("url") val entryUrl: String
)
