package com.fizhu.bikeappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fizhu.bikeappconcept.data.models.Bike
import com.fizhu.bikeappconcept.databinding.ItemListBikeBinding

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
            setImage(data.image?:"", ivBike)
            tvType.text = type
            root.setOnClickListener { callBack.invoke(data) }
        }
    }

    private fun setImage(url: String, iv: ImageView) {
        Glide.with(context)
            .asBitmap()
            .load(context.resources.getIdentifier(url, "drawable", context.packageName))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(iv)
    }
}