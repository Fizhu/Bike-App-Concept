package com.fizhu.bikeappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.data.raw.BikeRaw
import com.fizhu.bikeappconcept.databinding.ItemListMenuSettingBinding
import com.fizhu.bikeappconcept.utils.ext.loadImageFromLocalResources

/**
 * Created by fizhu on 15,July,2020
 * https://github.com/Fizhu
 */

class SettingAdapter(
    private val context: Context,
    private val callBack: (position: Int) -> Unit
) : RecyclerView.Adapter<SettingAdapter.ViewHolder>() {

    private val titles = listOf(
        context.getString(R.string.edit),
        context.getString(R.string.change),
        context.getString(R.string.logout)
    )

    private val icons = listOf(
        R.drawable.ic_edit,
        R.drawable.ic_key,
        R.drawable.ic_logout
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListMenuSettingBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = titles.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListMenuSettingBinding.bind(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            ivIcon.loadImageFromLocalResources(icons[position])
            tvTitle.text = titles[position]

            root.setOnClickListener { callBack.invoke(position) }
        }
    }
}