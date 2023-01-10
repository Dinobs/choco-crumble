package com.example.chococrumble

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.databinding.ActivityMainBinding
import com.example.chococrumble.model.Category
import com.example.chococrumble.request.GetCategoriesRequest
import com.example.chococrumble.ui.CategoriesAdapter
import com.example.chococrumble.ui.CategoryActivity

class MainActivity : AppCompatActivity() {
    private lateinit var categoryListRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var categoryBinding: ActivityMainBinding
    private var getCategoriesRequest: GetCategoriesRequest = GetCategoriesRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)

        categoryListRecyclerView = categoryBinding.categoryList

        getCategoriesRequest.request(::displayCategories)
    }

    private fun displayCategories(categories: List<Category>) {
        categories?.let { categories ->
            runOnUiThread {
                refreshView(categories)
            }
        }
    }

    private fun refreshView(categories: List<Category>) {
        categoriesAdapter = CategoriesAdapter(categories, ::openCategoryActivity)
        categoryListRecyclerView.adapter = categoriesAdapter
        categoryListRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun openCategoryActivity(category: String) {
        val intent = Intent(applicationContext, CategoryActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }
}