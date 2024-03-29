package com.valdosz.pokemon_list.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.valdosz.pokemon_list.R
import com.valdosz.pokemon_list.databinding.ItemPokemonBinding
import com.valdosz.pokemon_list.utils.getImage
import com.valdosz.pokemon_list.model.data.Result


/**
 * Created by ByHil on 24/09/2023
 */

class PokemonViewHolder(private val viewModel: ViewModel, view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPokemonBinding.bind(view)

    fun create(parent: ViewGroup): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(viewModel, view)
    }

    @SuppressLint("SetTextI18n")
    fun bind(data: Result?) {
        if (data != null) {
            if (data.url != null) {
                Glide.with(itemView).load(getImage(data.url!!.substring(data.url!!.length - 3, data.url!!.length - 1))).diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.photo)
            }

            binding.name.text = data.name
        }
    }
}