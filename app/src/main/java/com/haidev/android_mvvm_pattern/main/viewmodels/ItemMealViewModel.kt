package com.haidev.android_mvvm_pattern.main.viewmodels

import android.content.Context
import androidx.databinding.ObservableField
import com.haidev.android_mvvm_pattern.databinding.ItemListMealBinding
import com.haidev.android_mvvm_pattern.main.model.CategoryMeal
import com.squareup.picasso.Picasso

class ItemMealViewModel(
    private val context: Context,
    model: CategoryMeal,
    binding: ItemListMealBinding
) {
    var title: ObservableField<String?> = ObservableField(model.strCategory)
    var desc: ObservableField<String?> = ObservableField(model.strCategoryDescription)

    init {
        Picasso.get().load(
            model.strCategoryThumb
        ).into(binding.ivMeal)
    }
}
