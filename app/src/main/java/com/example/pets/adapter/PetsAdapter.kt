package com.example.pets.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pets.R
import com.example.pets.model.Pets
import kotlinx.android.synthetic.main.pets_list_item.view.*

class PetsAdapter(private val petsList: List<Pets>, private val context: Context) :
    RecyclerView.Adapter<PetsViewHolder>() {

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        holder.tvPetName.text = petsList[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        return PetsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.pets_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return petsList.size
    }
}

class PetsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvPetName: TextView = view.tv_pet_name
}