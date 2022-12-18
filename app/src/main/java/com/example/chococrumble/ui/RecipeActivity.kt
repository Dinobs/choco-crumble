package com.example.chococrumble.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.databinding.ActivityRecipeBinding
import com.example.chococrumble.model.Recipe
import com.example.chococrumble.request.GetRecipeRequest
import com.squareup.picasso.Picasso

class RecipeActivity: AppCompatActivity() {
    private lateinit var recipeBinding: ActivityRecipeBinding

    private lateinit var recipeTitleTextView: TextView
    private lateinit var recipeYoutubeTextView: TextView
    private lateinit var recipeImageView: ImageView
    private lateinit var recipeInstructionTextView: TextView

    private lateinit var ingredientListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeBinding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(recipeBinding.root)

        recipeTitleTextView = recipeBinding.recipeTitleTextview
        recipeYoutubeTextView  = recipeBinding.recipeYoutubeTextview
        recipeImageView = recipeBinding.recipeImageview
        recipeInstructionTextView = recipeBinding.recipeInstructionTextview
        ingredientListRecyclerView = recipeBinding.ingredientList

        val recipeId: Int = intent.getIntExtra("recipe_id", -1)
        val getRecipeRequest = GetRecipeRequest()
        getRecipeRequest.request(recipeId, ::displayRecipe)
    }

    private fun displayRecipe(recipe: Recipe) {
        runOnUiThread {
            refreshView(recipe)
        }
    }

    private fun refreshView(recipe: Recipe) {
        recipeTitleTextView.text = recipe.name
        recipeYoutubeTextView.text  = recipe.youtube
        Picasso.get().load(recipe.thumb).into(recipeImageView)
        recipeInstructionTextView.text = recipe.instructions

        ingredientListRecyclerView.adapter = IngredientsAdapter(recipe.ingredients)
        ingredientListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}