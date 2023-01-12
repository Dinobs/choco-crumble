package com.example.chococrumble.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.databinding.ActivityCategoryBinding
import com.example.chococrumble.model.Recipe
import com.example.chococrumble.request.GetRecipesRequest
import com.example.chococrumble.utils.NetworkChecker

class CategoryActivity: AppCompatActivity()  {
    private lateinit var recipeListRecyclerView: RecyclerView
    private lateinit var recipesAdapter: RecipesAdapter

    private lateinit var recipeBinding: ActivityCategoryBinding
    private var getRecipesRequest: GetRecipesRequest = GetRecipesRequest()

    private lateinit var categoryTextView: TextView
    private lateinit var categoryDescriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkChecker.checkInternetConnection(applicationContext)

        recipeBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(recipeBinding.root)

        recipeListRecyclerView = recipeBinding.recipeList
        categoryTextView = recipeBinding.categoryTitleTextview
        categoryDescriptionTextView = recipeBinding.categoryDescriptionItemTextview

        val category: String? = intent.getStringExtra("category")
        val description: String? = intent.getStringExtra("description")
        categoryTextView.text = category
        categoryDescriptionTextView.text = description

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
        recipesAdapter = RecipesAdapter(recipes, ::openRecipeActivity)
        recipeListRecyclerView.adapter = recipesAdapter
        recipeListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun openRecipeActivity(recipeId: Int) {
        val intent = Intent(applicationContext, RecipeActivity::class.java)
        intent.putExtra("recipe_id", recipeId)
        startActivity(intent)
    }
}