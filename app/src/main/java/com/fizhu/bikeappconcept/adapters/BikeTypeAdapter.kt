package com.fizhu.bikeappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fizhu.bikeappconcept.databinding.ItemListBikeTypeBinding

/**
 * Created by fizhu on 13,July,2020
 * https://github.com/Fizhu
 */
class BikeTypeAdapter(
    private val list: List<String>?,
    private val context: Context,
    private val callBack: (type: Int) -> Unit
) : RecyclerView.Adapter<BikeTypeAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemListBikeTypeBinding.inflate(LayoutInflater.from(context), parent, false).root)

    override fun getItemCount(): Int = list?.size?:3

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListBikeTypeBinding.bind(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val data = list[position]
        with(holder.binding) {
        }
    }
}