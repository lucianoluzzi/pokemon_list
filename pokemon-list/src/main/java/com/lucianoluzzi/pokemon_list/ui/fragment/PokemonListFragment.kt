package com.lucianoluzzi.pokemon_list.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lucianoluzzi.navigation.Navigator
import com.lucianoluzzi.pokemon_list.databinding.FragmentPokemonListBinding
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry
import com.lucianoluzzi.pokemon_list.ui.adapter.PokemonAdapter
import com.lucianoluzzi.pokemon_list.ui.viewmodel.ListResponseState
import com.lucianoluzzi.pokemon_list.ui.viewmodel.PokemonListViewModel

class PokemonListFragment(
    private val viewModel: PokemonListViewModel,
    private val navigator: Navigator
) : Fragment() {

    private val binding by lazy {
        val inflater = LayoutInflater.from(requireContext())
        FragmentPokemonListBinding.inflate(inflater)
    }

    private val pokemonAdapter = PokemonAdapter {
        navigator.navigateToPokemonDetails(this, it.name)
    }.also {
        it.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.pokemons.observe(viewLifecycleOwner) {
            when (it) {
                is ListResponseState.Success -> showList(it.value)
                is ListResponseState.Error -> showError(it.error)
            }
        }

        return binding.root
    }

    private fun showList(list: List<PokemonEntry>) = with(binding) {
        progress.isVisible = false
        error.isVisible = false
        pokemons.isVisible = true

        pokemonAdapter.submitList(list)
    }

    private fun showError(errorMessage: String?) = with(binding) {
        errorMessage?.let { message ->
            progress.isVisible = false
            error.isVisible = true
            error.text = message
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemons.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.pokemons.adapter = pokemonAdapter
    }
}