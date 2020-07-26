package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.data.models.Bike
import com.fizhu.bikeappconcept.databinding.FragmentMainBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class MainFragment : BaseFragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onInit() {
        childFragmentManager.findFragmentById(R.id.fragment_nav_host_main)
            ?.findNavController()?.let {
                NavigationUI.setupWithNavController(
                    binding?.bottomNav!!,
                    it
                )
            }
        binding?.bottomNav?.setOnNavigationItemReselectedListener { }
    }

    fun gotToProfile() {
        binding?.bottomNav?.selectedItemId = R.id.menu_account
    }
}