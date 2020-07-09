package com.fizhu.bikeappconcept.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.FragmentRegisterBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.observe
import com.fizhu.bikeappconcept.utils.ext.toast
import com.fizhu.bikeappconcept.viewmodels.RegisterViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding3.widget.textChangeEvents
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.util.concurrent.TimeUnit

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
    private var isFullNameValid = false
    private var isUsernameValid = false
    private var isPasswordValid = false
    private var isRepeatPasswordValid = false

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
        onClick()
        setImageProfile()
        initValidation()
        observe(viewModel.isUsernameExist) {
            if (it) {
                isUsernameValid = false
                binding?.tilUsername?.isHelperTextEnabled = true
                binding?.tilUsername?.helperText = getString(R.string.username_exist)
                checkvalid()
            } else {
                isUsernameValid = true
                binding?.tilUsername?.isHelperTextEnabled = false
                binding?.tilUsername?.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_check_24)
                checkvalid()
            }
        }

        binding?.ivProfile?.setOnClickListener {
            ImagePicker
                .create(this)
                .single()
                .returnMode(ReturnMode.CAMERA_ONLY)
                .theme(R.style.ImagePickerTheme)
                .folderMode(true)
                .enableLog(false)
                .start()
        }

        observe(viewModel.isRegitered) {
            if (it) {
                requireContext().toast("You've been registered, try to login !")
                findNavController().navigateUp()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val image = ImagePicker.getFirstImageOrNull(data)
            val file = File(image.path)
            val uri = Uri.fromFile(file).toString()
            viewModel.photo.value = uri
            Glide.with(this)
                .load(Uri.parse(uri))
                .error(R.drawable.default_image_profile)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
                .into(binding?.ivProfile!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initValidation() {
        validation(binding?.etFullname!!, binding?.tilFullname!!, 0)
        validation(binding?.etUsername!!, binding?.tilUsername!!, 1)
        validation(binding?.etPw!!, binding?.tilPw!!, 2)
        validation(binding?.etPwRepeat!!, binding?.tilPwRepeat!!, 3)
    }

    private fun validation(et: TextInputEditText, til: TextInputLayout, type: Int) {
        val observable = et.textChangeEvents()
        compositeDisposable.add(observable
            .skip(1)
            .debounce(400, TimeUnit.MILLISECONDS)
            .map { it.text }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (it.length < 6) {
                    til.isHelperTextEnabled = true
                    til.helperText = getString(R.string.invalid_validation)
                    when (type) {
                        0 -> {
                            isFullNameValid = false
                        }
                        1 -> {
                            isUsernameValid = false
                        }
                        2 -> {
                            isPasswordValid = false
                        }
                        3 -> {
                            isRepeatPasswordValid = false
                        }
                    }
                } else {
                    when (type) {
                        0 -> {
                            isFullNameValid = true
                            til.isHelperTextEnabled = false
                        }
                        1 -> {
                            viewModel.checkUsername()
                        }
                        2 -> {
                            isPasswordValid = true
                            til.isHelperTextEnabled = false
                        }
                        3 -> {
                            isRepeatPasswordValid = true
                            til.isHelperTextEnabled = false
                        }
                    }
                }

            }
            .subscribe { checkvalid() })
    }


    private fun checkvalid() {
        var isPwMatch = false

        // check password
        val pwLen = viewModel.password.value?.length ?: 0
        if (pwLen > 5) {
            if (viewModel.password.value == viewModel.repeatPassword.value) {
                binding?.tilPwRepeat?.isHelperTextEnabled = false
                isPwMatch = true
            } else {
                binding?.tilPwRepeat?.helperText = getString(R.string.didnt_match_validation)
                isPwMatch = false
            }
        }
        binding?.btnRegis?.isEnabled =
            isFullNameValid && isUsernameValid && isPasswordValid && isRepeatPasswordValid && isPwMatch

    }

    private fun onClick() {
        binding?.toolbar?.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun setImageProfile() {
        Glide.with(this)
            .load(R.drawable.default_image_profile)
            .error(R.drawable.default_image_profile)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
            .into(binding?.ivProfile!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}