package dev.claucookielabs.kotlinreposapp

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
