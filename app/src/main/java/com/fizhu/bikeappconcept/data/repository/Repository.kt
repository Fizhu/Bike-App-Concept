package com.fizhu.bikeappconcept.data.repository

import com.fizhu.bikeappconcept.data.models.Bike
import com.fizhu.bikeappconcept.data.models.User
import io.reactivex.Observable

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
interface Repository {
    fun getUserByUsernamePassword(username: String, password: String): Observable<List<User>>
    fun getUserByUsername(username: String): Observable<List<User>>
    fun getUserById(id: Int): Observable<List<User>>
    fun getBikeById(id: Int): Observable<List<Bike>>
    fun getIsLogin() : Boolean
    fun setIsLogin(isLogin: Boolean)
    fun insertUser(user: User)
    fun getAllUsers(): Observable<List<User>>
    fun getAllBike(): Observable<List<Bike>>
    fun count(): Observable<Int>
    fun getId(): String?
    fun setId(id: String)
    fun updatePhoto(id: Int, photo: String)
    fun insertBike(bike: Bike)
    fun deleteBikeById(id: Int)
}