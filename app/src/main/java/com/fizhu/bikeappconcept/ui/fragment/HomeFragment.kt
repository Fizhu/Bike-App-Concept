package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.adapters.BikeTypeAdapter
import com.fizhu.bikeappconcept.databinding.FragmentHomeBinding
import com.fizhu.bikeappconcept.utils.ViewPager2PageTransformation
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.toast
import com.fizhu.bikeappconcept.viewmodels.HomeViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.math.abs

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class HomeFragment : BaseFragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private var binding: FragmentHomeBinding? = null
    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding?.viewModel = this.viewModel
        return binding?.root
    }

    override fun onInit() {
        viewModel.getUserData()
        initViewPager()
    }

    private fun initViewPager() {
        val adapterType =
            BikeTypeAdapter(requireContext()) {
                requireContext().toast("Boo")
            }
        with(binding?.vpBike!!) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            adapter = adapterType
        }
        adapterType.notifyDataSetChanged()

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
        binding?.vpBike?.setPageTransformer { page, position ->
            val viewPager = page.parent.parent as ViewPager2
            val offset = position * -(2 * offsetPx + pageMarginPx)
            if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
            page.apply {
                translationY = abs(position) * 50f
                scaleX = 1f
                scaleY = 1f
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}