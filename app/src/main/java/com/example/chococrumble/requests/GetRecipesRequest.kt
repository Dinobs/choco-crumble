package com.example.chococrumble.requests

import android.util.Log
import com.example.chococrumble.responses.RecipesResponse
import com.example.chococrumble.models.Recipe
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class GetRecipesRequest {
    private val url: String = "https://themealdb.com/api/json/v1/1/filter.php?c="

    private val client = OkHttpClient()

    fun request(category: String?, callback: (List<Recipe>) -> Unit) {
        val tmpUrl = URL(url + category)
        val request = Request.Builder().url(tmpUrl).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.e("OKHTTP", it) }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val recipesResponse: RecipesResponse = parseRecipesResponse(it)
                    recipesResponse.recipes?.let { recipes ->
                        callback(recipes)
                    }
                }
            }
        })
    }

    private fun parseRecipesResponse(json: String): RecipesResponse {
        return Gson().fromJson(json, RecipesResponse::class.java)
    }
}
