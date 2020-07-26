package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.Bike
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.SingleLiveEvent
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.route

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class DetailViewModel(
    private val repository: Repository
) : BaseViewModel() {

    val bike: MutableLiveData<Bike> = MutableLiveData()
    val type: MutableLiveData<String> = MutableLiveData()

    private val _isFav = SingleLiveEvent<Boolean>()
    val isFav: LiveData<Boolean>
        get() = _isFav

    fun addToFav() {
        bike.value?.let {
            repository.insertBike(it)
        }
    }

    fun removeFromFav() {
        bike.value?.id?.let {
            repository.deleteBikeById(it)
        }
    }

    fun checkFav() {
        bike.value?.id?.let { id ->
            compositeDisposable.route(repository.getBikeById(id),
                io = {
                    if (it.isNotEmpty()) {
                        _isFav.postValue(true)
                    } else {
                        _isFav.postValue(false)
                    }
                },
                error = {
                    _isFav.postValue(true)
                }
            )
        }
    }
}