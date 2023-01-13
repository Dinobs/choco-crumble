package com.example.chococrumble.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.adapters.RecipesAdapter
import com.example.chococrumble.databinding.ActivityCategoryBinding
import com.example.chococrumble.models.Recipe
import com.example.chococrumble.requests.GetRecipesRequest
import com.example.chococrumble.utils.NetworkChecker
import com.google.android.material.progressindicator.CircularProgressIndicator

class CategoryActivity: AppCompatActivity()  {
    private lateinit var recipeListRecyclerView: RecyclerView
    private lateinit var recipesAdapter: RecipesAdapter

    private lateinit var categoryBinding: ActivityCategoryBinding
    private var getRecipesRequest: GetRecipesRequest = GetRecipesRequest()

    private lateinit var categoryTextView: TextView
    private lateinit var categoryDescriptionTextView: TextView
    
    private lateinit var circularProgressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryBinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)

        NetworkChecker.checkInternetConnection(applicationContext)

        circularProgressIndicator = categoryBinding.progressCircular

        recipeListRecyclerView = categoryBinding.recipeList
        categoryTextView = categoryBinding.categoryTitleTextview
        categoryDescriptionTextView = categoryBinding.categoryDescriptionItemTextview

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
        circularProgressIndicator.visibility = View.GONE
    }

    private fun openRecipeActivity(recipeId: Int) {
        val intent = Intent(applicationContext, RecipeActivity::class.java)
        intent.putExtra("recipe_id", recipeId)
        startActivity(intent)
    }

}