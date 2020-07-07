package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.FragmentLoginBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.doExitApp
import com.fizhu.bikeappconcept.viewmodels.LoginViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by fizhu on 07,July,2020
 * https://github.com/Fizhu
 */
class LoginFragment : BaseFragment() {

    private val viewModel by viewModel<LoginViewModel>()
    private var binding: FragmentLoginBinding? = null
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }
    private var isEmailValid = false
    private var isPwValid = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        binding?.viewModel = this.viewModel
        return binding?.root
    }

    override fun onInit() {
        binding?.btnLogin?.isEnabled = false
        handleBackPressed()
        onClick()
    }

//    private fun validation(et: TextInputEditText, til: TextInputLayout, type: Int) {
//        val observable = et.textChangeEvents()
//        compositeDisposable.add(observable
//            .skip(1)
//            .debounce(400, TimeUnit.MILLISECONDS)
//            .filter { it.text.isNotEmpty() }
//            .map { it.text }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnNext {
//                when {
//                    it.length < 5 -> {
//                        til.helperText = getString(R.string.invalid_validation)
//                        when (type) {
//                            1 -> isEmailValid = false
//                            2 -> isPwValid = false
//                        }
//                    }
//                    else -> {
//                        til.isHelperTextEnabled = false
//                        when (type) {
//                            1 -> isEmailValid = true
//                            2 -> isPwValid = true
//                        }
//                    }
//                }
//            }
//            .subscribe {
//                binding?.btnLogin?.isEnabled = isEmailValid && isPwValid
//            })
//    }

    private fun onClick() {
        binding?.tvSignup?.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
    }

    private fun handleBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                doExitApp(activity!!)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.etEmail?.text?.clear()
        binding?.etPw?.text?.clear()
        compositeDisposable.clear()
    }
}