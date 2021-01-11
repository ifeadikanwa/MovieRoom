package com.example.movieroom

import android.app.Application
import timber.log.Timber

//Setting up timber to allow better logging experience
class TimberApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}