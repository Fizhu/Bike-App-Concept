package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fizhu.bikeappconcept.databinding.FragmentSettingBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class SettingFragment : BaseFragment() {

    private var binding: FragmentSettingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onInit() {
        binding?.toolbar?.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}