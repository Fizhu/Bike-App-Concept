package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.SingleLiveEvent
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.route

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class RegisterViewModel(
    private val repository: Repository
): BaseViewModel(){

    val photo: MutableLiveData<String> = MutableLiveData()
    val fullname: MutableLiveData<String> = MutableLiveData()
    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val repeatPassword: MutableLiveData<String> = MutableLiveData()
    private val _isUsernameExist = SingleLiveEvent<Boolean>()
    val isUsernameExist: LiveData<Boolean>
        get() = _isUsernameExist

    fun checkUsername() {
        compositeDisposable.route(repository.getUserByUsername(username.value?:""),
            main = {
                _isUsernameExist.postValue(true)
            },
            error = {
                _isUsernameExist.postValue(true)
            }
        )
    }

    fun submitRegister() {
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