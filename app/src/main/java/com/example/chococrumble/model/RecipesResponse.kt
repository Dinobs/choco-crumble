package com.example.chococrumble.model

import com.google.gson.annotations.SerializedName

class RecipesResponse {
    @SerializedName("meals")
    var recipes: List<Recipe>? = null
}
