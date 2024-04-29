package com.fizhu.bikeappconcept.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fizhu.bikeappconcept.data.models.User
import com.fizhu.bikeappconcept.data.repository.Repository
import com.fizhu.bikeappconcept.utils.SingleLiveEvent
import com.fizhu.bikeappconcept.utils.base.BaseViewModel
import com.fizhu.bikeappconcept.utils.ext.route

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */

class LoginViewModel(
    private val repository: Repository
) : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    private val _isLoggedIn = SingleLiveEvent<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    init {
        addAndCheckDummyUser()
    }

    fun submitLogin() {
        compositeDisposable.route(repository.getUserByUsernamePassword(
            username.value ?: "",
            password.value ?: ""
        ),
            io = {
                if (it.isNotEmpty()) {
                    repository.setId(it[0].id.toString())
                    repository.setIsLogin(true)
                    _isLoggedIn.postValue(true)
                } else {
                    _isLoggedIn.postValue(false)
                }
            },
            error = {
                _isLoggedIn.postValue(false)
            }
        )
    }

    private fun addAndCheckDummyUser() {
        compositeDisposable.route(repository.getUserByUsernamePassword("userdemo", "123456"),
            io = {
                if (it.isEmpty()) {
                    addUserDemo()
                }
            },
            error = {
                addUserDemo()
                it.printStackTrace()
            }
        )
    }

    private fun addUserDemo() {
        repository.insertUser(
            User(
                id = null,
                name = "User Demo",
                username = "userdemo",
                password = "123456",
                photo = ""
            )
        )
    }

}