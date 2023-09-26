package com.yuzu.pokemondetail.model.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.valdosz.pokemon_list.model.datasource.PokemonDataSource
import com.valdosz.pokemon_list.model.data.Result
import com.valdosz.pokemon_list.model.repository.PokemonRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ByHil on 24/09/2023
 */

class PokemonDataSourceFactory(private val pokemonRepository: PokemonRepository, private val compositeDisposable: CompositeDisposable): DataSource.Factory<Int, Result>() {
    val pokemonDataSourceLiveData = MutableLiveData<PokemonDataSource>()

    override fun create(): DataSource<Int, Result> {
        val pokemonDataSource = PokemonDataSource(pokemonRepository, compositeDisposable)
        pokemonDataSourceLiveData.postValue(pokemonDataSource)
        return pokemonDataSource
    }
}