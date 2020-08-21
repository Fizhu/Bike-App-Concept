package com.fizhu.bikeappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fizhu.bikeappconcept.data.models.Bike
import com.fizhu.bikeappconcept.databinding.ItemListBikeBinding
import com.fizhu.bikeappconcept.utils.ext.loadImageFromLocalResourcesWithIdentifier

/**
 * Created by fizhu on 15,July,2020
 * https://github.com/Fizhu
 */

class BikeAdapter(
    private val context: Context,
    private val callBack: (bike: Bike) -> Unit
) : RecyclerView.Adapter<BikeAdapter.ViewHolder>() {

    private val list: MutableList<Bike> = mutableListOf()

    fun setData(listBike: List<Bike>) {
        list.clear()
        list.addAll(listBike)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListBikeBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListBikeBinding.bind(view)
        var currentVelocity = 0f

        val rotation: SpringAnimation = SpringAnimation(view, SpringAnimation.ROTATION)
            .setSpring(
                SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            )
            .addUpdateListener { _, _, velocity ->
                currentVelocity = velocity
            }

        val translationY: SpringAnimation = SpringAnimation(view, SpringAnimation.TRANSLATION_Y)
            .setSpring(
                SpringForce()
                    .setFinalPosition(0f)
                    .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
                    .setStiffness(SpringForce.STIFFNESS_LOW)
            )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        with(holder.binding) {
            tvName.text = data.name
            val type = when (data.type) {
                0 -> "Road Bike"
                1 -> "Mountain Bike"
                else -> "Trick Bike"
            }

            ivBike.loadImageFromLocalResourcesWithIdentifier(data.image ?: "")
            tvType.text = type
            cardView.setOnClickListener { callBack.invoke(data) }
        }
    }
}