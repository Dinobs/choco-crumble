package com.example.chococrumble.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.R
import com.example.chococrumble.model.Ingredient


class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView = itemView.findViewById(R.id.ingredient_name_textview)
    var measureTextView: TextView = itemView.findViewById(R.id.ingredient_measure_textview)
}

class IngredientsAdapter(private val ingredients: List<Ingredient>?) : RecyclerView.Adapter<IngredientsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item, parent, false)
        return IngredientsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, index: Int) {
        if (ingredients != null) {
            holder.nameTextView.text = ingredients[index].name
            holder.measureTextView.text = ingredients[index].measure
        }
    }

    override fun getItemCount(): Int {
        return ingredients?.count() ?: 0
    }
}