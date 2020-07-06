package com.fizhu.bikeappconcept.utils.ext

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */

fun CompositeDisposable.delay(long: Long, action: () -> Unit) {
    val disposable = Observable.just(long)
        .subscribeOn(Schedulers.io())
        .delay(long, TimeUnit.MILLISECONDS)
        .map {
            return@map action
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            it.invoke()
        }
    add(disposable)
}

fun doBack( success: (() -> Unit)? = null, error: (() -> Unit)? = null,action: () -> Unit): Disposable {
    return Completable.fromAction { action.invoke() }
        .subscribeOn(Schedulers.io())
        .subscribe({
            success?.invoke()
        }, {
            error?.invoke()
        })
}

fun doUi( success: (() -> Unit)? = null, error: (() -> Unit)? = null,action: () -> Unit): Disposable {
    return Completable.fromAction { action.invoke() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            success?.invoke()
        }, {
            error?.invoke()
        })
}