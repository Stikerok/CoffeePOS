package com.hfad.coffeepos

import android.app.Application
import com.hfad.coffeepos.di.appModule
import com.hfad.coffeepos.di.viewModelModel
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, viewModelModel))
    }

    companion object {
        lateinit var instance: App
            private set
    }
}