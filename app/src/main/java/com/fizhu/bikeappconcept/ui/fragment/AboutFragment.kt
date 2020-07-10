package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fizhu.bikeappconcept.databinding.FragmentAboutBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class AboutFragment : BaseFragment() {

    private var binding: FragmentAboutBinding? = null
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onInit() {

    }
}