package com.main.marriage_list.helper

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object SharedPrefHelper {
    lateinit var preferences: SharedPreferences
    private const val userObject = "user_model"

    fun with(application: Application) {
        preferences = application.getSharedPreferences(
            userObject, Context.MODE_PRIVATE
        )
    }

    fun <T> put(`object`: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        preferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = preferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}