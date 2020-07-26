package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.Bike
import com.fizhu.bikeappconcept.utils.base.BaseViewModel

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class DetailViewModel : BaseViewModel() {

    val bike: MutableLiveData<Bike> = MutableLiveData()
    val type: MutableLiveData<String> = MutableLiveData()

    init {
        type.value = when (bike.value?.type) {
            0 -> "Road Bike"
            1 -> "Mountain Bike"
            else -> "Trick Bike"
        }
    }
}