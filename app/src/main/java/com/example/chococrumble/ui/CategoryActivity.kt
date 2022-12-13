package com.example.chococrumble.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.databinding.ActivityCategoryBinding
import com.example.chococrumble.model.Recipe
import com.example.chococrumble.request.GetRecipesRequest

class CategoryActivity: AppCompatActivity()  {
    private lateinit var recipeListRecyclerView: RecyclerView
    private lateinit var recipesAdapter: RecipesAdapter

    private lateinit var recipeBinding: ActivityCategoryBinding
    private var getRecipesRequest: GetRecipesRequest = GetRecipesRequest()

    private lateinit var categoryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(recipeBinding.root)

        recipeListRecyclerView = recipeBinding.recipeList
        categoryTextView = recipeBinding.categoryTitleTextview

        val category: String? = intent.getStringExtra("category")
        categoryTextView.text = category

        getRecipesRequest.request(category, ::displayRecipes)


    }

    private fun displayRecipes(recipes: List<Recipe>) {
        recipes?.let { recipes ->
            runOnUiThread {
                refreshView(recipes)
            }
        }
    }

    private fun refreshView(recipes: List<Recipe>) {
        recipesAdapter = RecipesAdapter(recipes)
        recipeListRecyclerView.adapter = recipesAdapter
        recipeListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}