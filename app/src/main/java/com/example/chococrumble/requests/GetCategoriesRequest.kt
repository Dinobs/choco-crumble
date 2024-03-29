package com.example.chococrumble.requests

import android.util.Log
import com.example.chococrumble.responses.CategoriesResponse
import com.example.chococrumble.models.Category
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class GetCategoriesRequest {

    private val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
    private val request = Request.Builder().url(url).build()
    private val client = OkHttpClient()

    fun request(callback: (List<Category>) -> Unit) {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.localizedMessage?.let { Log.e("OKHTTP", it) }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    val categoriesResponse: CategoriesResponse = parseCategoriesResponse(it)
                    categoriesResponse.categories?.let { categories ->
                        callback(categories)
                    }
                }
            }
        })
    }

    private fun parseCategoriesResponse(json: String): CategoriesResponse {
        return Gson().fromJson(json, CategoriesResponse::class.java)
    }
}