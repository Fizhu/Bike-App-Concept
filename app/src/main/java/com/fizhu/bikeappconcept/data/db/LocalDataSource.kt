package com.fizhu.bikeappconcept.data.db

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

    val getAllUsers = userDao.all

    fun insert(user: User) {
        doBack(
            action = {
                userDao.insert(user)
            },
            success = { logi("success insert user to db") },
            error = { loge("failed insert user to db") }
        )
    }

    fun getUserByUsernamePassword(username: String, password: String) = userDao.getUserByUsernamePassword(username, password)

    fun getUserByUsername(username: String) = userDao.getUserByUsername(username)

    fun getUserById(id: Int) = userDao.getUserById(id)

    fun updatePhoto(id: Int, photo: String) = userDao.updatePhoto(id, photo)

}