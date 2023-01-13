package com.example.chococrumble

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chococrumble.databinding.ActivityMainBinding
import com.example.chococrumble.models.Category
import com.example.chococrumble.requests.GetCategoriesRequest
import com.example.chococrumble.adapters.CategoriesAdapter
import com.example.chococrumble.activities.CategoryActivity
import com.example.chococrumble.utils.NetworkChecker
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity : AppCompatActivity() {
    private lateinit var categoryListRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var categoryBinding: ActivityMainBinding
    private var getCategoriesRequest: GetCategoriesRequest = GetCategoriesRequest()

    private lateinit var circularProgressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        categoryBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(categoryBinding.root)

        circularProgressIndicator = categoryBinding.progressCircular

        NetworkChecker.checkInternetConnection(applicationContext)

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
        circularProgressIndicator.visibility = View.GONE
    }

    private fun openCategoryActivity(category: String?, description: String?) {
        val intent = Intent(applicationContext, CategoryActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("description", description)
        startActivity(intent)
    }
}