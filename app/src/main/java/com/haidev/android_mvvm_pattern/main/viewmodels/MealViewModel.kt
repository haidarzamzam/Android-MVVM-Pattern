package com.haidev.android_mvvm_pattern.main.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.haidev.android_mvvm_pattern.main.model.MealModel
import com.haidev.android_mvvm_pattern.networks.repositories.MealRepository

class MealViewModel(application: Application) : AndroidViewModel(application) {
    var isLoading: ObservableField<Boolean> = ObservableField()
    var cekMealResponse: MutableLiveData<MealModel> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    private var mainRepository = MealRepository()

    fun getMeal() {
        isLoading.set(true)
        mainRepository.getMeal({
            isLoading.set(false)
            cekMealResponse.postValue(it)
        }, {
            isLoading.set(false)
            error.postValue(it)
        }
        )
    }


    override fun onCleared() {
        super.onCleared()
        mainRepository.cleared()
    }
}