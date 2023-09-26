package com.valdosz.pokemon_list.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.valdosz.pokemon_list.R
import com.valdosz.pokemon_list.databinding.SkeletonPokemonBinding
import com.valdosz.pokemon_list.model.State

/**
 * Created by ByHil on 24/09/2023
 */

class SkeletonViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = SkeletonPokemonBinding.bind(view)

    fun create(parent: ViewGroup): SkeletonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.skeleton_pokemon, parent, false)
        return SkeletonViewHolder(view)
    }

    fun bind(status: State?) {
        Log.e("skeleton", "bind:State = $status")
        binding.skeletonLayout.visibility = if (status == State.LOADING) View.VISIBLE else View.GONE
        binding.txtError.visibility = if (status == State.ERROR || status == State.DONE) View.VISIBLE else View.GONE

        binding.skeletonConstraint.isEnabled = status == State.LOADING
    }
}