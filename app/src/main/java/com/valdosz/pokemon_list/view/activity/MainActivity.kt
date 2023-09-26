package com.valdosz.pokemon_list.view.activity

import android.os.Bundle
import com.valdosz.pokemon_list.R
import com.valdosz.pokemon_list.view.PokemonFragment

class MainActivity : BaseViewActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_content,
                    PokemonFragment()
                ).commit()
        }
    }
}