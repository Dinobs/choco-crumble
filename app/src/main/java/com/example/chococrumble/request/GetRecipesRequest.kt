package com.example.chococrumble.request

import android.util.Log
import com.example.chococrumble.model.RecipesResponse
import com.example.chococrumble.model.Recipe
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class GetRecipesRequest {
    private val url = URL("https://themealdb.com/api/json/v1/1/filter.php?c=Seafood")
    private val request = Request.Builder().url(url).build()
    private val client = OkHttpClient()

    fun request(callback: (List<Recipe>) -> Unit) {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    var recipesResponse: RecipesResponse? = parseRecipesResponse(it)
                    recipesResponse?.recipes?.let { recipes ->
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
