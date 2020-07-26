package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.FragmentDetailBinding
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.color
import com.fizhu.bikeappconcept.utils.ext.observe
import com.fizhu.bikeappconcept.utils.ext.toast
import com.fizhu.bikeappconcept.viewmodels.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class DetailFragment : BaseFragment() {

    private val viewModel by viewModel<DetailViewModel>()
    private var binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()
    private var isFav = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        binding?.viewModel = this.viewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onInit() {
        binding?.toolbar?.setNavigationOnClickListener { findNavController().navigateUp() }
        args.bike?.let {
            viewModel.bike.postValue(it)
            viewModel.type.postValue(
                when (it.type) {
                    0 -> "Road Bike"
                    1 -> "Mountain Bike"
                    else -> "Trick Bike"
                }
            )
        }
        observe(viewModel.isFav) {
            isFav = it
            binding?.btnFav?.let { imageView ->
                if (isFav) {
                    imageView.setColorFilter(requireContext().color(R.color.colorPrimary))
                } else {
                    imageView.setColorFilter(requireContext().color(R.color.colorOnBackground))
                }
            }
        }
        binding?.btnFav?.let {
            it.setOnClickListener { _ ->
                isFav = if (isFav) {
                    it.setColorFilter(requireContext().color(R.color.colorOnBackground))
                    viewModel.removeFromFav()
                    requireContext().toast("Removed from Favourite")
                    false
                } else {
                    it.setColorFilter(requireContext().color(R.color.colorPrimary))
                    viewModel.addToFav()
                    requireContext().toast("Added to Favourite")
                    true
                }
            }
        }
        observe(viewModel.bike) {
            viewModel.checkFav()
            binding?.ivBike?.let { imageView ->
                Glide.with(requireContext())
                    .asBitmap()
                    .load(
                        requireContext().resources.getIdentifier(
                            it.image,
                            "drawable",
                            requireContext().packageName
                        )
                    )
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imageView)
            }
        }
    }

}