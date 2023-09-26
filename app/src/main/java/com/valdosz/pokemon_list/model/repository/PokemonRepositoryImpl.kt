package com.valdosz.pokemon_list.model.repository

import com.valdosz.pokemon_list.model.api.PokemonAPI
import com.valdosz.pokemon_list.model.data.Pokemon
import io.reactivex.Single

/**
 * Created by ByHil on 24/09/2023
 */

class PokemonRepositoryImpl(private val api: PokemonAPI): PokemonRepository {
    override fun pokemon(offset: Int, limit: Int): Single<Pokemon> {
        return api.pokemon(offset, limit)
    }
}