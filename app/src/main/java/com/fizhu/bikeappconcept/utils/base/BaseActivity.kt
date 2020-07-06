package com.fizhu.bikeappconcept.utils.base

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.fizhu.bikeappconcept.utils.ext.toast

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
abstract class BaseActivity: AppCompatActivity() {

    fun onUnexpectedError() {
        toast("Error")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun hideSoftKeyboard() {
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val view = this.currentFocus
        if (view != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}