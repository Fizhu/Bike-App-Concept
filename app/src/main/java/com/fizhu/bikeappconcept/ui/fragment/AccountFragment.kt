package com.fizhu.bikeappconcept.ui.fragment

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.FragmentAccountBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.observe
import com.fizhu.bikeappconcept.utils.ext.toast
import com.fizhu.bikeappconcept.viewmodels.AccountViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class AccountFragment : BaseFragment() {

    private val viewModel by viewModel<AccountViewModel>()
    private var binding: FragmentAccountBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_account, container, false
        )
        binding?.viewModel = this.viewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onInit() {
        binding?.tvTitleAbout?.paintFlags =
            binding?.tvTitleAbout!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding?.tvLastest?.paintFlags =
            binding?.tvTitleAbout!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding?.toolbar?.setOnMenuItemClickListener {
            if (it.itemId == R.id.setting) {
                parentFragment?.parentFragment?.findNavController()
                    ?.navigate(R.id.action_mainFragment_to_settingFragment)
                return@setOnMenuItemClickListener true
            } else {
                return@setOnMenuItemClickListener false
            }
        }
        binding?.btnCam?.setOnClickListener {
            ImagePicker
                .create(this)
                .single()
                .returnMode(ReturnMode.CAMERA_ONLY)
                .theme(R.style.ImagePickerTheme)
                .folderMode(true)
                .enableLog(false)
                .start()
        }
        observe(viewModel.isSuccess) {
            if (it) {
                viewModel.getUserData()
                requireActivity().toast("Update photo success")
            } else {
                requireActivity().toast("Update photo failed")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val image = ImagePicker.getFirstImageOrNull(data)
            val file = File(image.path)
            val uri = Uri.fromFile(file).toString()
            viewModel.updatePhoto(uri)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}