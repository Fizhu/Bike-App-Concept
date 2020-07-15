package com.fizhu.bikeappconcept.ui.activity

import android.content.Intent
import android.os.Bundle
import com.fizhu.bikeappconcept.data.pref.UserSession
import com.fizhu.bikeappconcept.databinding.ActivityHomeBinding
import com.fizhu.bikeappconcept.utils.base.BaseActivity

/**
 * Created by fizhu on 05,July,2020
 * https://github.com/Fizhu
 */
class HomeActivity: BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun logout() {
        UserSession.clearSession()
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}