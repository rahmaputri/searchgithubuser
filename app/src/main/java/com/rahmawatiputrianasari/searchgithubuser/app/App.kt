package com.rahmawatiputrianasari.searchgithubuser.app

import android.app.Application
import com.rahmawatiputrianasari.searchgithubuser.app.di.AppComponent
import com.rahmawatiputrianasari.searchgithubuser.app.di.AppModule
import com.rahmawatiputrianasari.searchgithubuser.app.di.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .plus(AppModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }
}