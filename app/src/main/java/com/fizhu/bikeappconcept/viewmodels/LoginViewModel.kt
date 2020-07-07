package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.base.BaseViewModel

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class LoginViewModel(
    private val repository: Repository
): BaseViewModel(){

    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    fun submitLogin() {
//        _networkstate.postValue(NetworkState.LOADING)
//        compositeDisposable.route(
//            repository.login(email.value!!, password.value!!),
//            io = {
//                if (it.status) {
//                    if (it.data != null) {
//                        repository.storeUserData(it.data)
//                        _networkstate.postValue(NetworkState.SUCCESS)
//                    } else {
//                        _networkstate.postValue(NetworkState.NULL)
//                    }
//                } else {
//                    _networkstate.postValue(NetworkState.FAILED)
//                }
//            },
//            error = {
//                loge(it.localizedMessage)
//                _networkstate.postValue(NetworkState.ERROR)
//            }
//        )
    }

}