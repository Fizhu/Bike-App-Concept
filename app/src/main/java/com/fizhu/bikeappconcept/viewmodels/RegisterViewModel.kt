package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.User
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.SingleLiveEvent
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.route
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class RegisterViewModel(
    private val repository: Repository
) : BaseViewModel() {

    val photo: MutableLiveData<String> = MutableLiveData()
    val fullname: MutableLiveData<String> = MutableLiveData()
    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val repeatPassword: MutableLiveData<String> = MutableLiveData()
    private val _isUsernameExist = SingleLiveEvent<Boolean>()
    val isUsernameExist: LiveData<Boolean>
        get() = _isUsernameExist

    private val _isRegitered = SingleLiveEvent<Boolean>()
    val isRegitered: LiveData<Boolean>
        get() = _isRegitered

    fun checkUsername() {
        compositeDisposable.route(repository.getUserByUsername(username.value ?: ""),
            io = {
                if (it.isNotEmpty()) {
                    _isUsernameExist.postValue(true)
                } else {
                    _isUsernameExist.postValue(false)
                }
            },
            error = {
                _isUsernameExist.postValue(true)
            }
        )
    }

    fun submitRegister() {
        repository.insertUser(
            User(
                id = null,
                name = fullname.value,
                username = username.value,
                password = password.value,
                photo = photo.value?:""
            )
        )
        _isRegitered.postValue(true)
    }

}