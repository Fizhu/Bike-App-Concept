package com.fizhu.bikeappconcept.data.pref

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */
class PrefDataSource {
    fun getIsLogin() : Boolean = UserSession.keyIsLogin
    fun setIsLogin(isLogin: Boolean) {
        UserSession.keyIsLogin = isLogin
    }
    fun getId() : String? = UserSession.keyId
    fun setId(id: String) {
        UserSession.keyId = id
    }
}