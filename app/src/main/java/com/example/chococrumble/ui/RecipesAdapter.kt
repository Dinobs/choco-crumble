package com.example.chococrumble.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.R
import com.example.chococrumble.model.Recipe

class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.recipe_item_textview)
}

class RecipesAdapter(private val recipes: List<Recipe>): RecyclerView.Adapter<RecipesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, index: Int) {
        holder.textView.text = recipes[index].name
    }

    override fun getItemCount(): Int {
        return recipes.count()
    }

}