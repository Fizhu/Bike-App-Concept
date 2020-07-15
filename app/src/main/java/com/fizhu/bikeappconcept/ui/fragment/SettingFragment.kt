package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizhu.bikeappconcept.adapters.SettingAdapter
import com.fizhu.bikeappconcept.databinding.FragmentSettingBinding
import com.fizhu.bikeappconcept.ui.activity.HomeActivity
import com.fizhu.bikeappconcept.utils.base.BaseFragment

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class SettingFragment : BaseFragment() {

    private var binding: FragmentSettingBinding? = null
    private lateinit var adapter: SettingAdapter

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
        initRv()
    }

    private fun initRv() {
        binding?.rv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rv?.setHasFixedSize(true)
        adapter = SettingAdapter(requireContext()) {
            when (it) {
                0 -> {  }
                1 -> {  }
                2 -> {(activity as HomeActivity).logout()}
            }
        }
        adapter.notifyDataSetChanged()
        binding?.rv?.adapter = adapter
    }
}