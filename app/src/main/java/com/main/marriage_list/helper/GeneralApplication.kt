package com.main.marriage_list.helper

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class GeneralApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = applicationContext
        FirebaseApp.initializeApp(this)

        SharedPrefHelper.with(this)
    }

    companion object {
        lateinit var instance: GeneralApplication
        lateinit var appContext: Context
    }
}