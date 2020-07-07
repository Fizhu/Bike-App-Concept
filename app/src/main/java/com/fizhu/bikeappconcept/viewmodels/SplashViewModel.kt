package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.base.BaseViewModel

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class SplashViewModel(
    private val repository: Repository
): BaseViewModel(){

    var isLogin: MutableLiveData<Boolean> = MutableLiveData()

    fun getValue() {
        isLogin.postValue(repository.getIsLogin())
    }

}