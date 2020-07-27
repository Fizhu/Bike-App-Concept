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

/**
 * Created by fizhu on 15,July,2020
 * https://github.com/Fizhu
 */

class AboutAdapter(
    private val context: Context,
    private val callBack: (position: Int) -> Unit
) : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {

    private val list: List<Int> = arrayListOf(0, 1, 2, 3)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemListAboutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListAboutBinding.bind(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        with(holder.binding) {
            when (position) {
                0 -> {
                    tvTitle.text = context.getString(R.string.rate)
                    set(R.drawable.ic_playstore, ivIcon)
                }
                1 -> {
                    tvTitle.text = context.getString(R.string.mail)
                    set(R.drawable.ic_baseline_email_24, ivIcon)
                }
                2 -> {
                    tvTitle.text = context.getString(R.string.github)
                    set(R.drawable.ic_github, ivIcon)
                }
                3 -> {
                    tvTitle.text = context.getString(R.string.figma)
                    set(R.drawable.ic_figma, ivIcon)
                }
            }
            root.setOnClickListener { callBack.invoke(data) }
        }
    }

    private fun set(image: Int, iv: ImageView) {
        iv.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                image
            )
        )
    }
}