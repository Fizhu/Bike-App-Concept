package com.fizhu.bikeappconcept.data.pref

import com.fizhu.bikeappconcept.App

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
object UserSession {

    private const val KEY_IS_LOGIN = "Pref.keyIsLogin"

    var keyIsLogin: Boolean
        get() = App.spUser.getBoolean(KEY_IS_LOGIN, false)
        set(isLogin) = App.spUser.edit().putBoolean(KEY_IS_LOGIN, isLogin).apply()

}