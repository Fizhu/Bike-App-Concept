package com.fizhu.bikeappconcept.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizhu.bikeappconcept.R
import com.fizhu.bikeappconcept.adapters.AboutAdapter
import com.fizhu.bikeappconcept.databinding.FragmentAboutBinding
import com.fizhu.bikeappconcept.utils.AppConstants
import com.fizhu.bikeappconcept.utils.base.BaseFragment
import com.fizhu.bikeappconcept.utils.ext.toast

/**
 * Created by fizhu on 09,July,2020
 * https://github.com/Fizhu
 */
class AboutFragment : BaseFragment() {

    private var binding: FragmentAboutBinding? = null
    private lateinit var adapter: AboutAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onInit() {
        initRv()
        binding?.toolbar?.setOnMenuItemClickListener {
            if (it.itemId == R.id.share) {
                val i = Intent()
                i.action = Intent.ACTION_SEND
                i.putExtra(Intent.EXTRA_TEXT, AppConstants.SHARE_CONTENT)
                i.type = "text/plain"
                startActivity(Intent.createChooser(i, getString(R.string.share_with)))
                return@setOnMenuItemClickListener true
            } else {
                return@setOnMenuItemClickListener false
            }
        }
    }

    private fun initRv() {
        binding?.rv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rv?.setHasFixedSize(true)
        adapter = AboutAdapter(requireContext()) {
            val i = Intent()
            when (it) {
                0 -> {
                    i.data = Uri.parse(AppConstants.PLAYSTORE_URL)
                    i.action = Intent.ACTION_VIEW
                    startActivity(i)
                }
                1 -> {
                    i.action = Intent.ACTION_SENDTO
                    i.data = Uri.parse(AppConstants.EMAIL)
                    i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                    try {
                        startActivity(i)
                    } catch (e: Exception) {
                        requireContext().toast(getString(R.string.email_app_not_found))
                    }
                }
                2 -> {
                    i.data = Uri.parse(AppConstants.GIT_HUB)
                    i.action = Intent.ACTION_VIEW
                    startActivity(i)
                }
                3 -> {
                    i.data = Uri.parse(AppConstants.FIGMA)
                    i.action = Intent.ACTION_VIEW
                    startActivity(i)
                }
            }
        }
        adapter.notifyDataSetChanged()
        binding?.rv?.adapter = adapter
    }
}