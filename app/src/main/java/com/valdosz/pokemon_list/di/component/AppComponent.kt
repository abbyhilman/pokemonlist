package com.valdosz.pokemon_list.di.component

import android.app.Application
import com.valdosz.pokemon_list.di.module.AppModule
import com.valdosz.pokemon_list.model.api.PokemonAPI
import com.valdosz.pokemon_list.model.repository.PokemonRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ByHil on 24/09/2023
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: Application)

    //Pokemon API
    fun pokemonAPI(): PokemonAPI
    fun pokemonRepository(): PokemonRepository
}