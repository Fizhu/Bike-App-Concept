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

/**
 * Created by fizhu on 13,July,2020
 * https://github.com/Fizhu
 */
class BikeTypeAdapter(
    private val context: Context,
    private val callBack: (type: Int) -> Unit
) : RecyclerView.Adapter<BikeTypeAdapter.ViewHolder>(){

    private val list: List<Int> = arrayListOf(0,1,2)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemListBikeTypeBinding.inflate(LayoutInflater.from(context), parent, false).root)

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListBikeTypeBinding.bind(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        with(holder.binding) {
            when (data) {
                0 -> {
                    setImage(BikeRaw.imgRoadbike, ivBike)
                    tvType.text = this.root.context.getString(R.string.roadbike)
                }
                1 -> {
                    setImage(BikeRaw.imgMtb, ivBike)
                    tvType.text = this.root.context.getString(R.string.mountain_bike)
                }
                2 -> {
                    setImage(BikeRaw.imgBmx, ivBike)
                    tvType.text = this.root.context.getString(R.string.trick_bike)
                }
            }
            cardView.setOnClickListener { callBack.invoke(data) }
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