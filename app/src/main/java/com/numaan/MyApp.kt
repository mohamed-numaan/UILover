package com.numaan

import android.app.Application
import com.numaan.assesmentrngdev.utils.PreferencesManager

class MyApp: Application(){
    override fun onCreate() {
        super.onCreate()
        //Initialize the PreferencesManager with the application context
        PreferencesManager.initialize(applicationContext)
    }
}