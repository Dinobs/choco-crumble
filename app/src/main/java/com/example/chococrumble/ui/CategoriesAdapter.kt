package com.example.chococrumble.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.R
import com.example.chococrumble.model.Category

class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.category_item_textview)
}

class CategoriesAdapter(private val categories: List<Category>, val onClick: (String) -> Unit): RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)

        return CategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, index: Int) {
        holder.textView.text = categories[index].name

        holder.textView.setOnClickListener {
            categories[index].name?.let { it1 -> onClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

}