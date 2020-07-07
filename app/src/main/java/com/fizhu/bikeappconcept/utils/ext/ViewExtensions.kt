package com.fizhu.bikeappconcept.utils.ext

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.fizhu.bikeappconcept.data.pref.UserSession
import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.dpf: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

//fun Activity.makeStatusBarTransparentFullScreen() {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//        window.apply {
//            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                decorView.systemUiVisibility =
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_VISIBLE
//                if (UserSession.keyTheme != AppCompatDelegate.MODE_NIGHT_YES) {
//                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_VISIBLE or View . SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                }
//            } else {
//                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            }
//            statusBarColor = Color.TRANSPARENT
//        }
//    }
//}

fun View.setMargin(left: Int, top: Int, right: Int, bottom: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(left, top, right, bottom)
    this.layoutParams = menuLayoutParams
}

fun BottomSheetBehavior<*>.expand(composite: CompositeDisposable) {
    composite.delay(500) {
        state = BottomSheetBehavior.STATE_EXPANDED
    }
}

fun BottomSheetBehavior<*>.collapse(composite: CompositeDisposable) {
    composite.delay(500) {
        state = BottomSheetBehavior.STATE_COLLAPSED
    }
}

fun BottomSheetBehavior<*>.hide() {
    if (!isHidden()) {
        state = BottomSheetBehavior.STATE_HIDDEN
    }
}

fun BottomSheetBehavior<*>.isCollapse(): Boolean {
    return state == BottomSheetBehavior.STATE_COLLAPSED
}

fun BottomSheetBehavior<*>.isExpand(): Boolean {
    return state == BottomSheetBehavior.STATE_EXPANDED
}

fun BottomSheetBehavior<*>.isHidden(): Boolean {
    return state == BottomSheetBehavior.STATE_HIDDEN
}

fun View.animTo(xy: String, size: Float) {
    ObjectAnimator.ofFloat(this, "translation$xy", size).apply {
        duration = 100
        start()
    }
}