package com.fizhu.bikeappconcept.ui.activity

import android.content.Intent
import android.os.Bundle
import com.fizhu.bikeappconcept.databinding.ActivitySplashBinding
import com.fizhu.bikeappconcept.utils.AppConstants
import com.fizhu.bikeappconcept.utils.base.BaseActivity
import com.fizhu.bikeappconcept.utils.ext.delay
import com.fizhu.bikeappconcept.utils.ext.observe
import com.fizhu.bikeappconcept.utils.ext.startActivityWithFade
import com.fizhu.bikeappconcept.viewmodels.SplashViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by fizhu on 05,July,2020
 * https://github.com/Fizhu
 */

class SplashActivity : BaseActivity() {

    private val viewModel by viewModel<SplashViewModel>()
    private lateinit var binding: ActivitySplashBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSplash()
    }

    private fun initSplash() {
        viewModel.getValue()
        observe(viewModel.isLogin) {
            compositeDisposable.delay(AppConstants.SPLASH_TIME_MILLISECOND) {
                if (it) {
                    initHome()
                } else {
                    initAuth()
                }
            }
        }
    }

    private fun initAuth() {
        startActivityWithFade(this, AuthActivity::class.java)
        finish()
    }

    private fun initHome() {
        startActivityWithFade(this, HomeActivity::class.java)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}
