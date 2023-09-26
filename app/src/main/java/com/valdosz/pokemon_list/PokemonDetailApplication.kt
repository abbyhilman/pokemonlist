package com.valdosz.pokemon_list

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.valdosz.pokemon_list.di.component.AppComponent
import com.valdosz.pokemon_list.di.component.DaggerAppComponent
import com.valdosz.pokemon_list.di.module.AppModule

/**
 * Created by ByHil on 24/09/2023
 */

class PokemonDetailApplication: Application() {
    lateinit var component: AppComponent

    fun getAppComponent(): AppComponent {
        return component
    }

    companion object {
        lateinit var instance: PokemonDetailApplication private set
    }

    operator fun get(context: Context): PokemonDetailApplication {
        return context.applicationContext as PokemonDetailApplication
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // DI
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        component.inject(this)
    }
}