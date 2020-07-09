package com.fizhu.bikeappconcept

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.fizhu.bikeappconcept.data.db.Db
import com.fizhu.bikeappconcept.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by fizhu on 06,July,2020
 * https://github.com/Fizhu
 */

class App : Application() {

    lateinit var db: Db

    override fun onCreate() {
        super.onCreate()
        context = this
        singleton = this

        db = Db.getInstance(this)

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
                /**
                 * The application [Context] made static.
                 * Do **NOT** use this as the context for a view,
                 * this is mostly used to simplify calling of resources
                 * (esp. String resources) outside activities.
                 */
        var context: Context? = null
            private set

        @SuppressLint("StaticFieldLeak")
        var singleton: App? = null
            private set

        val getInstance: App?
            get() = singleton

        val spUser: SharedPreferences
            get() = getInstance!!.getSharedPreferences("myUserPref", Context.MODE_PRIVATE)
    }

}