package com.haidev.android_mvvm_pattern.main.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidev.android_mvvm_pattern.R
import com.haidev.android_mvvm_pattern.databinding.ActivityMealBinding
import com.haidev.android_mvvm_pattern.main.adapter.ItemMealAdapter
import com.haidev.android_mvvm_pattern.main.model.CategoryMeal
import com.haidev.android_mvvm_pattern.main.model.MealModel
import com.haidev.android_mvvm_pattern.main.viewmodels.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var mealBinding: ActivityMealBinding
    private lateinit var vmMeal: MealViewModel

    private lateinit var adapter: ItemMealAdapter
    private var listMeal: MutableList<CategoryMeal> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initRecyclerView()
        initSwipeRefresh()
    }

    private fun initBinding() {
        mealBinding = DataBindingUtil.setContentView(this, R.layout.activity_meal)
        vmMeal = ViewModelProviders.of(this).get(MealViewModel::class.java)
        mealBinding.meal = vmMeal
        vmMeal.getMeal()

        vmMeal.cekMealResponse.observe(this, Observer {
            onListDataChange(it)
        })
        vmMeal.error.observe(this, Observer {
            handlingError(it)
        })
    }

    private fun initRecyclerView() {
        mealBinding.swipe.isRefreshing = true
        val lManager = LinearLayoutManager(this)
        mealBinding.rvMeal.layoutManager = lManager
        mealBinding.rvMeal.setHasFixedSize(true)

        adapter = ItemMealAdapter(
            this,
            listMeal
        )
        mealBinding.rvMeal.adapter = adapter
    }

    private fun initSwipeRefresh() {
        mealBinding.swipe.setOnRefreshListener {
            mealBinding.swipe.isRefreshing = false
            vmMeal.getMeal()
        }
    }

    private fun onListDataChange(it: MealModel?) {
        mealBinding.swipe.isRefreshing = false
        listMeal.clear()
        listMeal.addAll(it!!.categories)
        mealBinding.rvMeal.post {
            adapter.notifyDataSetChanged()
        }
    }

    private fun handlingError(it: Throwable?) {
        mealBinding.swipe.isRefreshing = false
        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }
}
