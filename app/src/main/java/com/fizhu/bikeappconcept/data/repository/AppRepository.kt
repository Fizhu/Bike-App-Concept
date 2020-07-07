package com.fizhu.bikeappconcept.data.repository

import com.fizhu.bikeappconcept.data.db.LocalDataSource
import com.fizhu.bikeappconcept.data.models.User
import io.reactivex.Observable

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
open class AppRepository constructor(
    private val db: LocalDataSource
) : Repository {

    override fun login(username: String, password: String): Observable<User> {
        TODO("Not yet implemented")
    }

}