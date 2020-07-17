package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.FragmentAboutBinding
import com.fizhu.bikeappconcept.databinding.FragmentListBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.observe
import io.reactivex.disposables.CompositeDisposable

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
                binding?.ivSelectedBike?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.road_bike))
                initRv(0)
            }
            1 -> {
                binding?.toolbar?.title = getString(R.string.mountain_bike)
                binding?.ivSelectedBike?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.mountain_bike))
                initRv(1)
            }
            2 -> {
                binding?.toolbar?.title = getString(R.string.trick_bike)
                binding?.ivSelectedBike?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.trick_bike))
                initRv(2)
            }
        }
    }

    private fun initRv(type: Int) {
//            val adapterHelp =
//                HelpAdapter(it, requireContext()) { help ->
//                    requireContext().toast(help.judul_help.toString())
//                }
//            with(binding?.layoutContentBeranda?.rvHelp!!) {
//                layoutManager = LinearLayoutManager(requireContext(), GridLayoutManager.HORIZONTAL, false)
//                setHasFixedSize(true)
//                adapter = adapterHelp
//            }
//            adapterHelp.notifyDataSetChanged()
        }
}