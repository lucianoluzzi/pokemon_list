package com.lucianoluzzi.pokemon_list.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry

class PokemonEntryDiffCallback : DiffUtil.ItemCallback<PokemonEntry>() {
    override fun areItemsTheSame(
        oldItem: PokemonEntry,
        newItem: PokemonEntry
    ) = oldItem.number == newItem.number

    override fun areContentsTheSame(
        oldItem: PokemonEntry,
        newItem: PokemonEntry
    ) = oldItem == newItem
}