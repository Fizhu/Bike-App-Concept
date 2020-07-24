package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.adapters.BikeAdapter
import com.fizhu.bikeappconcept.data.raw.BikeRaw
import com.fizhu.bikeappconcept.databinding.FragmentListBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.loge
import com.fizhu.bikeappconcept.utils.ext.toast

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class ListFragment : BaseFragment() {

    private var binding: FragmentListBinding? = null
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onInit() {
        binding?.toolbar?.setNavigationOnClickListener { findNavController().navigateUp() }
        when (args.type) {
            0 -> {
                binding?.toolbar?.title = getString(R.string.roadbike)
                binding?.ivSelectedBike?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.road_bike
                    )
                )
                initRv(0)
            }
            1 -> {
                binding?.toolbar?.title = getString(R.string.mountain_bike)
                binding?.ivSelectedBike?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.mountain_bike
                    )
                )
                initRv(1)
            }
            2 -> {
                binding?.toolbar?.title = getString(R.string.trick_bike)
                binding?.ivSelectedBike?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.trick_bike
                    )
                )
                initRv(2)
            }
        }
    }

    private fun initRv(type: Int) {
        val list = when (type) {
            0 -> BikeRaw.listRoadBike
            1 -> BikeRaw.listMtb
            else -> BikeRaw.listBmx
        }
        val adapterBike =
            BikeAdapter(requireContext()) {
                requireContext().toast(it.name.toString())
            }
        binding?.rv?.let {
            with(it) {
                layoutManager =
                    LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = adapterBike
            }
        }
        adapterBike.notifyDataSetChanged()
        adapterBike.setData(list)
    }
}