package com.fizhu.bikeappconcept.utils.ext

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by fizhu on 23,May,2020
 * Hvyz.anbiya@gmail.com
 */

fun <T> CompositeDisposable.route(observable: Observable<T>, io: ((T) -> Unit)? = null,
                                  main: ((T) -> Unit)? = null,
                                  error: ((throwable: Throwable) -> Unit)? = null) {

    add(
        observable
            .subscribeOn(Schedulers.io())
            .doOnNext {
                io?.invoke(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                main?.invoke(it)
            }, {
                error?.invoke(it)
            })

    )
}