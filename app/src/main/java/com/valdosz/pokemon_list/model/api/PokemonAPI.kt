package com.valdosz.pokemon_list.model.api

import com.valdosz.pokemon_list.model.data.Pokemon
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ByHil on 24/09/2023
 */

interface PokemonAPI {
    /**
     * Get List Pokemon
     * */
    @GET(value = "pokemon")
    fun pokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Single<Pokemon>
}