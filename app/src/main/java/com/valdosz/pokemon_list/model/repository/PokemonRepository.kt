package com.valdosz.pokemon_list.model.repository

import com.valdosz.pokemon_list.model.data.Pokemon
import io.reactivex.Single

/**
 * Created by ByHil on 24/09/2023
 */

interface PokemonRepository {
    fun pokemon(offset: Int, limit: Int): Single<Pokemon>
}