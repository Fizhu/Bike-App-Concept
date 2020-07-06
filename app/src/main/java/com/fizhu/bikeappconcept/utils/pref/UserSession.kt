package com.fizhu.bikeappconcept.utils.pref

import android.text.TextUtils
import com.fizhu.bikeappconcept.App
import com.google.gson.Gson

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
object UserSession {

    private const val KEY_TOKEN = "Pref.KeyToken"
    private const val KEY_ID_USER = "Pref.KeyId"
    private const val KEY_PROFILE_USER = "Pref.UserData"
    private const val KEY_THEME = "Pref.Theme"

//    fun getUserData(): User? {
//        val s = App.spUser.getString(KEY_PROFILE_USER, "")
//        return if (TextUtils.isEmpty(s)) {
//            null
//        } else {
//            Gson().fromJson(s, User::class.java)
//        }
//    }
//
//    fun setUserData(dataUser: User) {
//        App.spUser.edit().putString(KEY_PROFILE_USER, Gson().toJson(dataUser))
//            .apply()
//    }

    var keyId: String?
        get() = App.spUser.getString(KEY_ID_USER, "")
        set(id) = App.spUser.edit().putString(KEY_ID_USER, id).apply()

    var keyToken: String?
        get() = App.spUser.getString(KEY_TOKEN, "")
        set(token) = App.spUser.edit().putString(KEY_TOKEN, token).apply()

    var keyTheme: Int
        get() = App.spUser.getInt(KEY_THEME, 0)
        set(token) = App.spUser.edit().putInt(KEY_THEME, token).apply()

    fun clearUserData() {
        App.spUser.edit().clear().apply()
    }
}