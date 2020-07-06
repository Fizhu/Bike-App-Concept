package com.fizhu.bikeappconcept.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.fizhu.bikeappconcept.databinding.ActivitySplashBinding
import com.fizhu.bikeappconcept.utils.base.BaseActivity
import com.fizhu.bikeappconcept.utils.ext.startActivityWithFade
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by fizhu on 05,July,2020
 * https://github.com/Fizhu
 */

class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModel()
    private lateinit var binding: ActivitySplashBinding
    private val compositeDisposable = CompositeDisposable()
    private var token: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initSplash() {
        viewModel.getValue()
        viewModel.token?.observe(this, Observer { token = it })
        compositeDisposable.delay(AppConstants.SPLASH_TIME_MILLISECOND) {
            if (token == "" || token == null) {
                viewModel.isFirstTime.observe(this, Observer {
                    if (it) {
                        findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
                    } else {
                        (activity as SplashActivity).initAuth()
                    }
                })
            } else {
                (activity as SplashActivity).initHome()
            }
        }
    }

    fun initAuth() {
        startActivityWithFade(this, AuthActivity::class.java)
        finish()
    }

    fun initAuthWithNoFade() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    fun initHome() {
        startActivityWithFade(this, HomeActivity::class.java)
        finish()
    }

}
