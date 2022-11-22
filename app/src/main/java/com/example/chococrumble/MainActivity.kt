package com.example.chococrumble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.databinding.ActivityMainBinding
import com.example.chococrumble.model.Category
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var categoryListRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
//    private var categoriesResponse: CategoriesResponse? = null

    private lateinit var categoryBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)

        categoryListRecyclerView = categoryBinding.categoryList

//        val url = URL("https://www.themealdb.com/api/json/v1/1/categories.php")
//        val request = Request.Builder().url(url).build()
//
//        val client = OkHttpClient()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("OKHTTP", e.localizedMessage)
//            }
//            override fun onResponse(call: Call, response: Response) {
//                response.body?.string()?.let {
//                    categoriesResponse = parseCategoriesResponse(it)
//                    displayCategories()
//                }
//            }
//        })
    }

//    private fun displayCategories() {
//        categoriesResponse?.categories?.let { categories ->
//            runOnUiThread {
//                refreshView(categories)
//            }
//        }
//    }
//
//    private fun parseCategoriesResponse(json: String): CategoriesResponse {
//        return Gson().fromJson(json, CategoriesResponse::class.java)
//    }

    private fun refreshView(categories: List<Category>) {
        categoriesAdapter = CategoriesAdapter(categories)
        categoryListRecyclerView.adapter = categoriesAdapter
        categoryListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }
}