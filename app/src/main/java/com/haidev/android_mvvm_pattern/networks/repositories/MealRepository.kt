package com.haidev.android_mvvm_pattern.networks.repositories

import com.haidev.android_mvvm_pattern.main.model.MainModel
import com.haidev.android_mvvm_pattern.networks.ApiObserver
import com.haidev.android_mvvm_pattern.networks.ServiceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealRepository {
    private val compositeDisposable = CompositeDisposable()
    private val apiService = ServiceFactory.create()

    fun getMeal(
        onResult: (MainModel) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        apiService.getMeal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<MainModel>(compositeDisposable) {
                override fun onApiSuccess(data: MainModel) {
                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }
            })
    }
}