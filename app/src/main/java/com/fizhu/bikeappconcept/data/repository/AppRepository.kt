package com.fizhu.bikeappconcept.data.repository

import com.fizhu.bikeappconcept.data.db.LocalDataSource
import com.fizhu.bikeappconcept.data.models.User
import com.fizhu.bikeappconcept.data.pref.PrefDataSource
import io.reactivex.Observable

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
open class AppRepository constructor(
    private val db: LocalDataSource,
    private val pref: PrefDataSource
) : Repository {

    override fun getUserByUsernamePassword(username: String, password: String): Observable<List<User>> = db.getUserByUsernamePassword(username, password)
    override fun getUserByUsername(username: String): Observable<List<User>> = db.getUserByUsername(username)
    override fun getUserById(id: Int): Observable<List<User>> = db.getUserById(id)
    override fun getIsLogin(): Boolean = pref.getIsLogin()
    override fun setIsLogin(isLogin: Boolean) = pref.setIsLogin(isLogin)
    override fun insertUser(user: User) = db.insert(user)
    override fun getAllUsers(): Observable<List<User>> = db.getAllUsers
    override fun getId(): String? = pref.getId()
    override fun setId(id: String) = pref.setId(id)
}