package com.fizhu.bikeappconcept.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.databinding.ItemListAboutBinding
import com.fizhu.bikeappconcept.databinding.ItemListMenuSettingBinding
import com.fizhu.bikeappconcept.utils.ext.loadImageFromLocalResources

/**
 * Created by fizhu on 15,July,2020
 * https://github.com/Fizhu
 */

class AboutAdapter(
    private val context: Context,
    private val callBack: (position: Int) -> Unit
) : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {

    private val titles = listOf(
        context.getString(R.string.rate),
        context.getString(R.string.mail),
        context.getString(R.string.github),
        context.getString(R.string.figma)
    )

    private val icons = listOf(
        R.drawable.ic_playstore,
        R.drawable.ic_baseline_email_24,
        R.drawable.ic_github,
        R.drawable.ic_figma
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListAboutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = titles.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListAboutBinding.bind(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {

            ivIcon.loadImageFromLocalResources(icons[position])
            tvTitle.text = titles[position]

            root.setOnClickListener { callBack.invoke(position) }
        }
    }
}