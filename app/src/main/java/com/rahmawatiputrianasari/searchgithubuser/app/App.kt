package com.rahmawatiputrianasari.searchgithubuser.app

import android.app.Application
import android.content.Context
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

    companion object {
        lateinit var ctx: Context
    }


    override fun onCreate() {
        super.onCreate()

        component.inject(this)
        ctx = this
    }
}