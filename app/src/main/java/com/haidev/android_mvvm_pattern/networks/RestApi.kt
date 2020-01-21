package com.haidev.android_mvvm_pattern.networks

import com.haidev.android_mvvm_pattern.main.model.MealModel
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {
    @GET("categories.php")
    fun getMeal(): Observable<MealModel>
}