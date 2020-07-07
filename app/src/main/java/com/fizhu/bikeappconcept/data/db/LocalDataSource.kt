package com.fizhu.bikeappconcept.data.db

import com.fizhu.bikeappconcept.App
import com.fizhu.bikeappconcept.data.models.User
import com.fizhu.bikeappconcept.utils.ext.doBack
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.logi

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */
open class LocalDataSource constructor(
    private val userDao: UserDao
) {

    fun insert(user: User) {
        doBack(
            action = {
                userDao.insert(user)
            },
            success = { logi("success insert user to db") },
            error = { loge("failed insert user to db") }
        )
    }

    fun getUserByUsernamePassword(username: String, password: String) {
        doBack(
            action = {
                userDao.getUserByUsernamePassword(username, password)
            },
            success = { logi("success get user by username & password from db") },
            error = { loge("failed get user from db") }
        )
    }

    fun getUserByUsername(username: String) {
        doBack(
            action = {
                userDao.getUserByUsername(username)
            },
            success = { logi("success get user by username from db") },
            error = { loge("failed iget user by username from db") }
        )
    }

    fun getUserById(id: Int) {
        doBack(
            action = {
                userDao.getUserById(id)
            },
            success = { logi("success get user by id from db") },
            error = { loge("failed iget user from by id db") }
        )
    }

}