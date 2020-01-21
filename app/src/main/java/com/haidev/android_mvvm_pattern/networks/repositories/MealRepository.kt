package com.haidev.android_mvvm_pattern.networks.repositories

import com.haidev.android_mvvm_pattern.main.model.MealModel
import com.haidev.android_mvvm_pattern.networks.ApiObserver
import com.haidev.android_mvvm_pattern.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealRepository {
    private val compositeDisposable = CompositeDisposable()
    private val apiService = ServiceFactory.create()

    fun getMeal(
        onResult: (MealModel) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        apiService.getMeal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<MealModel>(compositeDisposable) {
                override fun onApiSuccess(data: MealModel) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }

    fun cleared() {
        compositeDisposable.clear()
    }
}