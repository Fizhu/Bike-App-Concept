package com.fizhu.bikeappconcept.ui.activity

import android.content.Intent
import android.os.Bundle
import com.fizhu.bikeappconcept.databinding.ActivitySplashBinding
import com.fizhu.bikeappconcept.utils.base.BaseActivity
import com.fizhu.bikeappconcept.utils.ext.startActivityWithFade

/**
 * Created by fizhu on 05,July,2020
 * https://github.com/Fizhu
 */

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        makeStatusBarTransparentFullScreen()
    }

    fun initAuth() {
//        startActivityWithFade(this, AuthActivity::class.java)
        finish()
    }

    fun initAuthWithNoFade() {
//        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    fun initHome() {
        startActivityWithFade(this, HomeActivity::class.java)
        finish()
    }

}
