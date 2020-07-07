package com.fizhu.bikeappconcept.data.repository

import com.fizhu.bikeappconcept.data.models.User
import io.reactivex.Observable

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
interface Repository {
    val isLogin: Boolean
    fun getUserByUsernamePassword(username: String, password: String): Observable<User>
    fun getUserByUsername(username: String): Observable<User>
    fun getUserById(id: Int): Observable<User>
    fun getIsLogin() : Boolean
    fun setIsLogin(isLogin: Boolean)
}