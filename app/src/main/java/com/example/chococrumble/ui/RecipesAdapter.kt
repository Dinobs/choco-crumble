package com.example.chococrumble.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.R
import com.example.chococrumble.model.Recipe
import com.squareup.picasso.Picasso

class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.recipe_item_textview)
    var imageView: ImageView = itemView.findViewById(R.id.recipe_imageview)
}

class RecipesAdapter(private val recipes: List<Recipe>, val onClick: (Int) -> Unit): RecyclerView.Adapter<RecipesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecipesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, index: Int) {
        holder.textView.text = recipes[index].name
        Picasso.get().load(recipes[index].thumb).into(holder.imageView)

        holder.itemView.setOnClickListener {
            recipes[index].id?.let { id -> onClick(id) }
        }
    }

    override fun getItemCount(): Int {
        return recipes.count()
    }
}