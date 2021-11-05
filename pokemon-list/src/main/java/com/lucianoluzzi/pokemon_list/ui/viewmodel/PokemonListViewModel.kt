package com.lucianoluzzi.pokemon_list.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucianoluzzi.network.domain.Response
import com.lucianoluzzi.pokemon_list.domain.entity.PokemonEntry
import com.lucianoluzzi.pokemon_list.domain.usecase.GetPokemonsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PokemonListViewModel(
    getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _pokemons = MutableLiveData<ListResponseState>()
    val pokemons: LiveData<ListResponseState> = _pokemons

    init {
        getPokemonsUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                _pokemons.value = getUIStateFromResponse(response)
            }
    }

    private fun getUIStateFromResponse(response: Response<List<PokemonEntry>>): ListResponseState {
        return when (response) {
            is Response.Error -> {
                ListResponseState.Error(response.message)
            }
            is Response.Success -> {
                ListResponseState.Success(response.data)
            }
            else -> throw IllegalStateException("Not covered state")
        }
    }
}