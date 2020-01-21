package com.haidev.android_mvvm_pattern.main.model

import com.google.gson.annotations.SerializedName


data class MainModel(
    @SerializedName("categories")
    val categories: List<Category>
)

data class Category(
    @SerializedName("idCategory")
    val idCategory: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String
)