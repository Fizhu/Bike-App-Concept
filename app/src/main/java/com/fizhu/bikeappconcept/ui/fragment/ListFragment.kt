package com.fizhu.bikeappconcept.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EdgeEffect
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.adapters.BikeAdapter
import com.fizhu.bikeappconcept.data.raw.BikeRaw
import com.fizhu.bikeappconcept.databinding.FragmentListBinding
import com.fizhu.bikeappconcept.utils.AppConstants
import com.fizhu.bikeappconcept.utils.base.BaseFragment

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
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailFragment(
                        it
                    )
                )
            }
        binding?.rv?.let {
            with(it) {
                layoutManager =
                    LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = adapterBike

                it.edgeEffectFactory = object : RecyclerView.EdgeEffectFactory() {
                    override fun createEdgeEffect(view: RecyclerView, direction: Int): EdgeEffect {
                        return object : EdgeEffect(view.context) {

                            override fun onPull(deltaDistance: Float) {
                                super.onPull(deltaDistance)
                                handlePull(deltaDistance)
                            }

                            override fun onPull(deltaDistance: Float, displacement: Float) {
                                super.onPull(deltaDistance, displacement)
                                handlePull(deltaDistance)
                            }

                            private fun handlePull(deltaDistance: Float) {
                                // This is called on every touch event while the list is scrolled with a finger.
                                // We simply update the view properties without animation.
                                val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                                val rotationDelta =
                                    sign * deltaDistance * AppConstants.OVERSCROLL_ROTATION_MAGNITUDE
                                val translationYDelta =
                                    sign * view.width * deltaDistance * AppConstants.OVERSCROLL_TRANSLATION_MAGNITUDE
                                view.forEachVisibleHolder { holder: BikeAdapter.ViewHolder ->
                                    holder.rotation.cancel()
                                    holder.translationY.cancel()
                                    holder.itemView.rotation += rotationDelta
                                    holder.itemView.translationY += translationYDelta
                                }
                            }

                            override fun onRelease() {
                                super.onRelease()
                                // The finger is lifted. This is when we should start the animations to bring
                                // the view property values back to their resting states.
                                view.forEachVisibleHolder { holder: BikeAdapter.ViewHolder ->
                                    holder.rotation.start()
                                    holder.translationY.start()
                                }
                            }

                            override fun onAbsorb(velocity: Int) {
                                super.onAbsorb(velocity)
                                val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
                                // The list has reached the edge on fling.
                                val translationVelocity =
                                    sign * velocity * AppConstants.FLING_TRANSLATION_MAGNITUDE
                                view.forEachVisibleHolder { holder: BikeAdapter.ViewHolder ->
                                    holder.translationY
                                        .setStartVelocity(translationVelocity)
                                        .start()
                                }
                            }
                        }
                    }
                }

                it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        recyclerView.forEachVisibleHolder { holder: BikeAdapter.ViewHolder ->
                            holder.rotation
                                // Update the velocity.
                                // The velocity is calculated by the vertical scroll offset.
                                .setStartVelocity(holder.currentVelocity - dx * AppConstants.SCROLL_ROTATION_MAGNITUDE)
                                // Start the animation. This does nothing if the animation is already running.
                                .start()
                        }
                    }
                })

            }
        }
        adapterBike.notifyDataSetChanged()
        adapterBike.setData(list)
    }

    inline fun <reified T : RecyclerView.ViewHolder> RecyclerView.forEachVisibleHolder(
        action: (T) -> Unit
    ) {
        for (i in 0 until childCount) {
            action(getChildViewHolder(getChildAt(i)) as T)
        }
    }

}