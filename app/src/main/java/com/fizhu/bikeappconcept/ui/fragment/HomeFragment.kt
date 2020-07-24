package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.adapters.BikeSelectedAdapter
import com.fizhu.bikeappconcept.adapters.BikeTypeAdapter
import com.fizhu.bikeappconcept.databinding.FragmentHomeBinding
import com.fizhu.bikeappconcept.utils.VerticalTextView
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.doExitApp
import com.fizhu.bikeappconcept.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class HomeFragment : BaseFragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding?.viewModel = this.viewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onInit() {
        handleBackPressed()
        viewModel.getUserData()
        binding?.ivProfile?.setOnClickListener { (parentFragment?.parentFragment as MainFragment).gotToProfile() }
        initViewPager()
    }

    private fun initViewPager() {
        val adapterSelected =
            BikeSelectedAdapter(requireContext())
        binding?.vpSelectedBike?.let {
            with(it) {
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                adapter = adapterSelected
            }
        }
        binding?.vpSelectedBike?.isUserInputEnabled = false

        val adapterType =
            BikeTypeAdapter(requireContext()) {
                parentFragment?.parentFragment?.findNavController()
                    ?.navigate(MainFragmentDirections.actionMainFragmentToListFragment(it))
            }
        binding?.vpBike?.let {
            with(it) {
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                adapter = adapterType

                val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
                val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
                setPageTransformer { page, position ->
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
        }
        adapterType.notifyDataSetChanged()

        val listTab: List<VerticalTextView?> = listOf(
            binding?.tvRoadbike,
            binding?.tvMtb,
            binding?.tvBmx
        )

        binding?.vpBike?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding?.vpSelectedBike?.setCurrentItem(position, true)
                listTab.forEachIndexed { index, verticalTextView ->
                    if (position == index) {
                        setTabColor(verticalTextView, true)
                    } else {
                        setTabColor(verticalTextView, false)
                    }
                }
            }

        })

        listTab.forEachIndexed { index, verticalTextView ->
            verticalTextView?.setOnClickListener {
                binding?.vpBike?.setCurrentItem(index, true)
            }
        }
    }

    private fun setTabColor(tv: VerticalTextView?, isSelected: Boolean) {
        if (isSelected) {
            tv?.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        } else {
            tv?.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorOnBackground))
        }
    }

    private fun handleBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                doExitApp(requireActivity())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}