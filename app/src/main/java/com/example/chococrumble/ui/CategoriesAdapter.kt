package com.example.chococrumble.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.R
import com.example.chococrumble.model.Category
import com.squareup.picasso.Picasso

class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.category_item_textview)
    var descriptionTextView: TextView = itemView.findViewById(R.id.category_description_item_textview)
    var imageView: ImageView = itemView.findViewById(R.id.category_imageview)
}

class CategoriesAdapter(private val categories: List<Category>, val onClick: (String) -> Unit): RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)

        return CategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, index: Int) {
        holder.textView.text = categories[index].name
        holder.descriptionTextView.text = categories[index].description
        Picasso.get().load(categories[index].thumb).into(holder.imageView)

        holder.itemView.setOnClickListener {
            categories[index].name?.let { it1 -> onClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

}