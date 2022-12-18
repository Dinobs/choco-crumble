package com.example.chococrumble.request

import android.util.Log
import com.example.chococrumble.model.Ingredient
import com.example.chococrumble.model.Recipe
import com.example.chococrumble.model.RecipesResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import okhttp3.*
import java.io.IOException
import java.net.URL

class GetRecipeRequest {
    private val url: String = "https://www.themealdb.com/api/json/v1/1/lookup.php?i="

    private val client = OkHttpClient()

    fun request(recipeId: Int?, callback: (Recipe) -> Unit) {
        val tmpUrl = URL(url + recipeId)
        val request = Request.Builder().url(tmpUrl).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    var recipesResponse: RecipesResponse? = parseRecipesResponse(it)
                    recipesResponse?.recipes?.let { recipes ->
                        callback(recipes[0])
                    }
                }
            }
        })
    }

    private fun parseRecipesResponse(json: String): RecipesResponse {
        var recipesResponse: RecipesResponse = Gson().fromJson(json, RecipesResponse::class.java)

        var ingredientsList: MutableList<Ingredient> = mutableListOf()

        val entries: JsonObject? = getRecipeEntries(json)

        if (entries != null) {
            for (entry: Map.Entry<String, JsonElement> in entries.entrySet()) {
                if (entry.key.startsWith("strIngredient") && entry.value.toString().isNotBlank()) {
                    val measureKey = entry.key.replace("strIngredient", "strMeasure")
                    val measure: String = entries.get(measureKey).asString

                    if (measure.isNotBlank()) {
                        ingredientsList.add(Ingredient(entry.value.asString, measure))
                    }
                }
            }
        }

        recipesResponse.recipes?.get(0)?.ingredients = ingredientsList

        return recipesResponse
    }

    private fun getRecipeEntries(json: String): JsonObject? {
        var jsonObject: JsonObject? = Gson().fromJson(json, JsonObject::class.java)
        if (jsonObject != null) {
            return jsonObject.get("meals").asJsonArray[0].asJsonObject
        }

        return null
    }
}