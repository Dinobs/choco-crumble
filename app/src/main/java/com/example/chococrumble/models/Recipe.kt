package com.example.chococrumble.models

import com.google.gson.annotations.SerializedName

class Recipe {
    @SerializedName("idMeal")
    var id: Int? = null
    @SerializedName("strMeal")
    var name: String? = null
    @SerializedName("strMealThumb")
    var thumb: String? = null
    @SerializedName("strDrinkAlternate")
    var drinkAlternate: String? = null
    @SerializedName("strArea")
    var area: String? = null
    @SerializedName("strInstructions")
    var instructions: String? = null
    @SerializedName("strYoutube")
    var youtube: String? = null
    @SerializedName("strTags")
    var tags: String? = null
    var ingredients: List<Ingredient>? = null

    override fun toString(): String {
        return "Recipe(id=$id, name=$name, thumb=$thumb, drinkAlternate=$drinkAlternate, area=$area, instructions=$instructions, tags=$tags, youtube=$youtube, ingredients=$ingredients)"
    }
}
