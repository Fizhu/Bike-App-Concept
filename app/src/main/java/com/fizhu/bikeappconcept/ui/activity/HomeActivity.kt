package com.fizhu.bikeappconcept.ui.activity

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.fizhu.bikeappconcept.R
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
        with(binding) {
            supportFragmentManager.findFragmentById(R.id.fragment_nav_host_home)
                ?.findNavController()?.let {
                    NavigationUI.setupWithNavController(bottomNav,
                        it
                    )
                }
        }
    }
}