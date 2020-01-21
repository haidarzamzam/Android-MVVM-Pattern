package com.haidev.android_mvvm_pattern.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haidev.android_mvvm_pattern.R
import com.haidev.android_mvvm_pattern.databinding.ItemListMealBinding
import com.haidev.android_mvvm_pattern.main.model.CategoryMeal
import com.haidev.android_mvvm_pattern.main.viewmodels.ItemMealViewModel

class ItemMealAdapter(
    private val context: Context,
    private var listMeal: MutableList<CategoryMeal>
) :
    RecyclerView.Adapter<ItemMealAdapter.ItemMealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemMealViewHolder {
        val binding: ItemListMealBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_list_meal,
                parent,
                false
            )
        return ItemMealViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return listMeal.size
    }

    override fun onBindViewHolder(holder: ItemMealViewHolder, position: Int) {
        val fixPosition = holder.adapterPosition
        holder.bindBinding(context, listMeal[fixPosition])
    }

    class ItemMealViewHolder(val binding: ItemListMealBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var viewModel: ItemMealViewModel

        fun bindBinding(context: Context, model: CategoryMeal) {
            viewModel = ItemMealViewModel(
                context,
                model,
                binding
            )
            binding.itemMeal = viewModel
            binding.executePendingBindings()
        }

    }
}