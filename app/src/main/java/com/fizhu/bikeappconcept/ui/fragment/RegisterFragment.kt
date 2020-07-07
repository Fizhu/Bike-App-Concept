package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.FragmentRegisterBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.viewmodels.RegisterViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */
class RegisterFragment : BaseFragment() {

    private val viewModel by viewModel<RegisterViewModel>()
    private var binding: FragmentRegisterBinding? = null
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )
        binding?.viewModel = this.viewModel
        return binding?.root
    }

    override fun onInit() {
        binding?.btnRegis?.isEnabled = false
        handleBackPressed()
        onClick()
    }

    private fun onClick() {
        binding?.toolbar?.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun handleBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        binding?.etEmail?.text?.clear()
//        binding?.etPw?.text?.clear()
        compositeDisposable.clear()
    }
}