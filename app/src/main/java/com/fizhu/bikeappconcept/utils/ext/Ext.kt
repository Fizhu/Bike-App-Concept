package com.fizhu.bikeappconcept.utils.ext

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fizhu.bikeappconcept.R
import timber.log.Timber
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Created by fizhu on 22,May,2020
 * Hvyz.anbiya@gmail.com
 */

private var exitTime: Long = 0

/** The loading progress dialog object */
var progressDialog: ProgressDialog? = null

fun Context.toast(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun startActivityWithFade(activity: Activity, clazz: Class<*>) {
    activity.startActivity(Intent(activity, clazz))
    activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}

fun doExitApp(activity: Activity) {
    if (System.currentTimeMillis() - exitTime > 2000) {
        Toast.makeText(activity, R.string.label_do_exit, Toast.LENGTH_SHORT).show()
        exitTime = System.currentTimeMillis()
    } else {
        activity.finish()
    }
}

/**
 * Simplify logging with timber, and make it into function !
 * @param pesan is the logging message
 * @param context is only to get the name of which context that provide the log message
 */
fun logi(pesan: String?) = Timber.tag("BIKE APP-LOG D").d("Message : $pesan")

fun logi(context: Context, pesan: String?) =
    Timber.tag("LOG D ${context.javaClass.simpleName}").d("Message : $pesan")

fun loge(pesan: String?) = Timber.tag("BIKE APP-LOG E").e("Message : $pesan")

fun loge(context: Context, pesan: String?) =
    Timber.tag("LOG E ${context.javaClass.simpleName}").e("Message : $pesan")

fun logw(pesan: String?) = Timber.tag("BIKE APP-LOG W").e("Message : $pesan")

/**
 * Shows a loading progress dialog.
 * @param context the context
 * @param message the dialog message string
 * @param onBackPressListener the back button press listener when loading is shown
 */
fun showProgressDialog(context: Context, message: String) {
    dismissProgressDialog()
    progressDialog = ProgressDialog(context)
    progressDialog!!.setMessage(message)
    progressDialog!!.setCancelable(false)
    if (context is Activity && !context.isFinishing) progressDialog!!.show()
}

/** Hides the currently shown loading progress dialog */
fun dismissProgressDialog() {
    if (progressDialog != null && progressDialog!!.isShowing) {
        progressDialog!!.dismiss()
        progressDialog = null
    }
}

/** makes visible a view. */
fun View.visible() {
    visibility = View.VISIBLE
}

/** makes gone a view. */
fun View.gone() {
    visibility = View.GONE
}

fun Context.color(resource: Int): Int {
    return ContextCompat.getColor(this, resource)
}

fun Fragment.color(resource: Int): Int {
    context?.let {
        return ContextCompat.getColor(it, resource)
    }
    return 0
}

val getAnimNavOption = navOptions {
    anim {
        enter = R.anim.fade_in
        exit = R.anim.fade_out
        popEnter = R.anim.fade_in
        popExit = R.anim.fade_out
    }
}

fun createDrawableFromView(context: Context, view: View): Bitmap? {
    val displayMetrics = DisplayMetrics()
    (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    view.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
    view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
    view.buildDrawingCache()
    val bitmap = Bitmap.createBitmap(
        view.measuredWidth,
        view.measuredHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}

fun Int.formatted(): String {
    var originalString = this.toString()
    val longValue: Long
    originalString = originalString.replace(",".toRegex(), "").replace(".", "")
    longValue = originalString.toLong()
    val formatter = NumberFormat.getInstance(Locale("en", "ID")) as DecimalFormat
    formatter.applyPattern("#,###,###,###")
    return formatter.format(longValue).replace(",", ".")
}

fun getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun ImageView.loadImageFromLocalResources(resourcesId: Int) {
    setImageDrawable(
        ContextCompat.getDrawable(
            context,
            resourcesId
        )
    )
}

fun ImageView.loadImageFromLocalResourcesWithIdentifier(path: String) {
    Glide.with(context)
        .asBitmap()
        .load(context.resources.getIdentifier(path, "drawable", context.packageName))
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}