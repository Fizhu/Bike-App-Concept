package com.fizhu.bikeappconcept.utils.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
abstract class BaseViewModel: ViewModel(), LifecycleObserver {

    protected val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        with(compositeDisposable) {
            clear()
            dispose()
        }
        super.onCleared()
    }
}