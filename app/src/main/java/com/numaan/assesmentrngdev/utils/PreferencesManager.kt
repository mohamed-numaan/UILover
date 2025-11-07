package com.numaan.assesmentrngdev.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by mohamednumaan on 09,December,2024
 */
object PreferencesManager {

    private const val PREFS_NAME = "ui_lover_preferences"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private lateinit var preferences: SharedPreferences

    fun initialize(context: Context){
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setLoggedIn(isLoggedIn: Boolean){
        with(preferences.edit()){
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            apply()
        }
    }

    fun isLoggedIn(): Boolean{
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

}