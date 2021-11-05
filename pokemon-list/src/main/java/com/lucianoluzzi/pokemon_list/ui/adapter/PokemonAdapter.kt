package com.lucianoluzzi.pokemon_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lucianoluzzi.pokemon_list.R
import com.lucianoluzzi.pokemon_list.databinding.ListItemPokemonBinding
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry

class PokemonAdapter(
    private val clickListener: (pokemon: PokemonEntry) -> Unit
) : ListAdapter<PokemonEntry, PokemonAdapter.PokemonViewHolder>(
    PokemonEntryDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPokemonBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(
            binding,
            clickListener
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonEntry = getItem(position)
        holder.bind(pokemonEntry)
    }

    class PokemonViewHolder(
        private val itemBinding: ListItemPokemonBinding,
        private val clickListener: (pokemon: PokemonEntry) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(pokemon: PokemonEntry) = with(itemBinding) {
            root.setOnClickListener {
                clickListener(pokemon)
            }
            image.load(pokemon.urlImage) {
                crossfade(true)
                placeholder(R.mipmap.ic_pokeball_foreground)
            }
            root.contentDescription = pokemon.name
            number.text = pokemon.number.toString()
            name.text = pokemon.name
        }
    }
}