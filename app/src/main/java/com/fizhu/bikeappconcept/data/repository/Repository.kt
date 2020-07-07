package com.fizhu.bikeappconcept.data.repository

import com.fizhu.bikeappconcept.data.models.User
import io.reactivex.Observable

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
interface Repository {
    fun login(username: String, password: String): Observable<User>
}