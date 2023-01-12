package com.example.chococrumble.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.databinding.ActivityRecipeBinding
import com.example.chococrumble.model.Recipe
import com.example.chococrumble.request.GetRecipeRequest
import com.example.chococrumble.utils.NetworkChecker
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.squareup.picasso.Picasso


class RecipeActivity: YouTubeBaseActivity() {
    private lateinit var recipeBinding: ActivityRecipeBinding

    private lateinit var recipeTitleTextView: TextView
    private lateinit var recipeImageView: ImageView
    private lateinit var recipeInstructionTextView: TextView
    private lateinit var youtubePlayerView: YouTubePlayerView

    private lateinit var ingredientListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkChecker.checkInternetConnection(applicationContext)

        recipeBinding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(recipeBinding.root)

        recipeTitleTextView = recipeBinding.recipeTitleTextview
        recipeImageView = recipeBinding.recipeImageview
        recipeInstructionTextView = recipeBinding.recipeInstructionTextview
        ingredientListRecyclerView = recipeBinding.ingredientList
        youtubePlayerView = recipeBinding.recipeYoutubePlayerview

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
        Picasso.get().load(recipe.thumb).into(recipeImageView)
        recipeInstructionTextView.text = recipe.instructions

        ingredientListRecyclerView.adapter = IngredientsAdapter(recipe.ingredients)
        ingredientListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)


        var onInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                youTubePlayer: YouTubePlayer,
                b: Boolean
            ) {
                var ytId = recipe.youtube?.split("?v=")?.get(1)
                Log.d("ytid", "id is $ytId")
                youTubePlayer.cueVideo(ytId)
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {
            }
        }

        if(recipe.youtube != null && recipe.youtube!!.isNotBlank()) {
            Log.d("ytid", recipe.youtube!!)
            youtubePlayerView.initialize("AIzaSyDsAli0TAc1zKTE4lJcH-vKV0jSW0KkofI",onInitializedListener);}
        else {
            Log.d("ytid", "youtube is null")
            youtubePlayerView.visibility = View.GONE
        }

    }
}