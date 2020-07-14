package com.fizhu.bikeappconcept.utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * Created by fizhu on 14,July,2020
 * https://github.com/Fizhu
 */
class ViewPager2PageTransformation : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
    val absPos = abs(position)
    page.apply {
        translationY = absPos * 50f
        translationX = absPos * 50f
        scaleX = 1f
        scaleY = 1f
    }
        when {
        position < -1 ->page.alpha = 0.1f
        position <= 1 -> {
            page.alpha = 0.2f.coerceAtLeast(1 - abs(position))
        }
        else -> page.alpha = 0.1f
    }
}
}