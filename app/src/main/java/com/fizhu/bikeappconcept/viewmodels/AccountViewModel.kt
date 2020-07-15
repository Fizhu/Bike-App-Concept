package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.User
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.SingleLiveEvent
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.doBack
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.logi
import com.fizhu.bikeappconcept.utils.ext.route
import com.google.gson.GsonBuilder

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class AccountViewModel(
    private val repository: Repository
): BaseViewModel(){

    val userData: MutableLiveData<User> = MutableLiveData()
    private val _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    init {
        getUserData()
    }

    fun getUserData() {
        compositeDisposable.route(repository.getUserById(repository.getId()?.toInt()?:0),
            io = {
                if (it.isNotEmpty()) {
                    userData.postValue(it[0])
                    val gson  = GsonBuilder().setPrettyPrinting().create()
                    loge(gson.toJson(it[0]))
                } else {
                    loge("Userdata is empty")
                }
            },
            error = {
                loge(it.localizedMessage)
            })
    }

    fun updatePhoto(photo: String) {
        doBack(
            action = {
                repository.updatePhoto(repository.getId()?.toInt()?:0, photo)
            },
            success = {
                _isSuccess.postValue(true)
                logi("update photo success")
            },
            error = {
                _isSuccess.postValue(false)
                loge("update photo failed")
            }
        )
    }

}