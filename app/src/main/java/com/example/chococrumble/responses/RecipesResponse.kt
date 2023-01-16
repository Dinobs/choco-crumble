package com.example.chococrumble.responses

import com.example.chococrumble.models.Recipe
import com.google.gson.annotations.SerializedName

class RecipesResponse {
    @SerializedName("meals")
    var recipes: List<Recipe>? = null
}
