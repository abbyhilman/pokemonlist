package com.valdosz.pokemon_list.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.valdosz.pokemon_list.PokemonDetailApplication
import com.valdosz.pokemon_list.databinding.FragmentPokemonBinding
import com.valdosz.pokemon_list.model.State
import com.valdosz.pokemon_list.model.data.Result
import com.valdosz.pokemon_list.model.datasource.PokemonDataSource
//import com.valdosz.pokemon_list.model.datasource.PokemonDataSourceFactory
import com.valdosz.pokemon_list.model.repository.PokemonRepository
import com.valdosz.pokemon_list.view.adapter.PokemonAdapter
import com.yuzu.pokemondetail.model.datasource.PokemonDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ByHil on 24/09/2023
 */

class PokemonViewModel: ViewModel() {
//    private val LOG_TAG = "Movie"
    var loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private val compositeDisposable = CompositeDisposable()
    private val pokemonRepository: PokemonRepository
    private val pokemonDataSourceFactory: PokemonDataSourceFactory

    var pokemonPagedList: LiveData<PagedList<Result>>
    private val pageSize = 5

    init {
        val appComponent = PokemonDetailApplication.instance.getAppComponent()
        pokemonRepository = appComponent.pokemonRepository()

        pokemonDataSourceFactory = PokemonDataSourceFactory(pokemonRepository, compositeDisposable)
        val config = PagedList.Config.Builder().setPageSize(pageSize).setInitialLoadSizeHint(pageSize).setEnablePlaceholders(false).build()
        pokemonPagedList = LivePagedListBuilder(pokemonDataSourceFactory, config).build()
    }

    fun retry() {
        pokemonDataSourceFactory!!.pokemonDataSourceLiveData.value?.retry()
    }

    fun getState(): LiveData<State> = Transformations.switchMap(
        pokemonDataSourceFactory!!.pokemonDataSourceLiveData,
        PokemonDataSource::state
    )

    private fun listIsEmpty(): Boolean {
        return pokemonPagedList.value?.isEmpty() ?: true
    }

    fun recyclerViewVisibility(binding: FragmentPokemonBinding, state: State, pokemonAdapter: PokemonAdapter) {
        if (listIsEmpty() && state == State.LOADING) {
            loading.value = true
            binding.recyclerview.visibility = View.GONE
            //binding.txtError.visibility = View.GONE

        } else if (listIsEmpty() && state == State.ERROR) {
            loading.value = false
            //binding.txtError.visibility = View.VISIBLE

        } else {
            loading.value = false
            binding.recyclerview.visibility = View.VISIBLE
            //binding.txtError.visibility = View.GONE
        }

        if (!listIsEmpty()) {
            pokemonAdapter.setState(state)
        }
    }
}