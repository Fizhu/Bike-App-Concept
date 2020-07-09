package com.fizhu.bikeappconcept.data.pref

import com.fizhu.bikeappconcept.App

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
object UserSession {

    private const val KEY_IS_LOGIN = "Pref.keyIsLogin"
    private const val KEY_ID = "Pref.keyId"

    var keyIsLogin: Boolean
        get() = App.spUser.getBoolean(KEY_IS_LOGIN, false)
        set(isLogin) = App.spUser.edit().putBoolean(KEY_IS_LOGIN, isLogin).apply()

    var keyId: String?
        get() = App.spUser.getString(KEY_ID, "")
        set(id) = App.spUser.edit().putString(KEY_ID, id).apply()

    fun clearSession() {
        App.spUser.edit().clear().apply()
    }

}