package com.main.marriage_list.helper

import android.app.Application
import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import com.main.marriage_list.di.AppComponent
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class GeneralApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = applicationContext
        SharedPrefHelper.with(this)
    }

    companion object {
        lateinit var instance: GeneralApplication
        lateinit var appContext: Context
    }
}