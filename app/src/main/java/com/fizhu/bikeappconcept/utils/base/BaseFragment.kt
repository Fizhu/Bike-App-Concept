package com.fizhu.bikeappconcept.utils.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
abstract class BaseFragment: Fragment() {

    private fun getBaseActivity() = activity as BaseActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInit()
    }

    fun onUnexpectedError() {
        getBaseActivity().onUnexpectedError()
    }

    abstract fun onInit()
}
