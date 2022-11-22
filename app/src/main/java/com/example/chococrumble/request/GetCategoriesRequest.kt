package com.example.chococrumble.request

import android.util.Log
import com.example.chococrumble.CategoriesResponse
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class GetCategoriesRequest {

    val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
    val request = Request.Builder().url(url).build()

    val client = OkHttpClient()

    private var categoriesResponse: CategoriesResponse? = null


    fun request() {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OKHTTP", e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {
                    categoriesResponse = parseCategoriesResponse(it)
                    displayCategories()
                }
            }
        })
    }

    private fun displayCategories() {
        categoriesResponse?.categories?.let { categories ->
            runOnUiThread {
                refreshView(categories)
            }
        }
    }

    private fun parseCategoriesResponse(json: String): CategoriesResponse {
        return Gson().fromJson(json, CategoriesResponse::class.java)
    }
}