package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.Bike
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.SingleLiveEvent
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.route

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class FavoriteViewModel(
    private val repository: Repository
): BaseViewModel(){

    val listBike: MutableLiveData<List<Bike>> = MutableLiveData()
    private val _isExist = SingleLiveEvent<Boolean>()
    val isExist: LiveData<Boolean>
        get() = _isExist

    fun getFav() {
        _isExist.postValue(false)
        compositeDisposable.route(repository.getAllBike(),
            io = {
                if (it.isNotEmpty()) {
                    _isExist.postValue(true)
                    listBike.postValue(it)
                } else {
                    _isExist.postValue(false)
                }
            },
            error = {
                _isExist.postValue(false)
                loge(it.localizedMessage)
            })
    }

}