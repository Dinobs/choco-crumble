package com.example.chococrumble.model

import com.google.gson.annotations.SerializedName

class Recipe {
    @SerializedName("idMeal")
    var id: Int? = null
    @SerializedName("strMeal")
    var name: String? = null
    @SerializedName("strMealThumb")
    var thumb: String? = null
}
