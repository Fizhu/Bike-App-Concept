package com.fizhu.bikeappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.data.raw.BikeRaw
import com.fizhu.bikeappconcept.databinding.ItemListBikeTypeBinding
import com.fizhu.bikeappconcept.utils.ext.loadImageFromLocalResourcesWithIdentifier

/**
 * Created by fizhu on 13,July,2020
 * https://github.com/Fizhu
 */
class BikeTypeAdapter(
    private val context: Context,
    private val callBack: (type: Int) -> Unit
) : RecyclerView.Adapter<BikeTypeAdapter.ViewHolder>() {

    private val types = listOf(
        context.getString(R.string.roadbike),
        context.getString(R.string.mountain_bike),
        context.getString(R.string.trick_bike)
    )

    private val images = listOf(
        BikeRaw.imgRoadbike,
        BikeRaw.imgMtb,
        BikeRaw.imgBmx
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListBikeTypeBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = types.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListBikeTypeBinding.bind(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            ivBike.loadImageFromLocalResourcesWithIdentifier(images[position])
            tvType.text = types[position]

            cardView.setOnClickListener { callBack.invoke(position) }
        }
    }
}