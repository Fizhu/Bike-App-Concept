package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.User
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.loge
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

}