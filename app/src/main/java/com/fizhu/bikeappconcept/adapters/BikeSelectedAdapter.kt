package com.fizhu.bikeappconcept.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.data.raw.BikeRaw
import com.fizhu.bikeappconcept.databinding.ItemListBikeTypeBinding
import com.fizhu.bikeappconcept.databinding.ItemSelectedBikeBinding
import com.fizhu.bikeappconcept.utils.ext.loadImageFromLocalResources

/**
 * Created by fizhu on 13,July,2020
 * https://github.com/Fizhu
 */
class BikeSelectedAdapter(
    private val context: Context
) : RecyclerView.Adapter<BikeSelectedAdapter.ViewHolder>(){

    private val images = listOf(
        R.drawable.road_bike,
        R.drawable.mountain_bike,
        R.drawable.trick_bike
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemSelectedBikeBinding.inflate(LayoutInflater.from(context), parent, false).root)

    override fun getItemCount(): Int = images.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSelectedBikeBinding.bind(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            ivSelectedBike.loadImageFromLocalResources(images[position])
        }
    }
}