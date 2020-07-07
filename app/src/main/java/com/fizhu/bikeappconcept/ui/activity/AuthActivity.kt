package com.fizhu.bikeappconcept.ui.activity

import android.content.Intent
import android.os.Bundle
import com.fizhu.bikeappconcept.databinding.ActivityAuthBinding
import com.fizhu.bikeappconcept.utils.base.BaseActivity

/**
 * Created by fizhu on 06,July,2020
 * https://github.com/Fizhu
 */
class AuthActivity: BaseActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun initHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}