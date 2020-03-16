package com.rahmawatiputrianasari.searchgithubuser.ui.splash.di

import com.rahmawatiputrianasari.searchgithubuser.app.di.ActivityScope
import com.rahmawatiputrianasari.searchgithubuser.ui.splash.SplashActivity
import com.rahmawatiputrianasari.searchgithubuser.ui.splash.SplashContract
import com.rahmawatiputrianasari.searchgithubuser.ui.splash.SplashPresenter
import com.rahmawatiputrianasari.searchgithubuser.ui.splash.SplashRouter
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    @ActivityScope
    fun router(activity: SplashActivity): SplashContract.Router = SplashRouter(activity)

    @Provides
    @ActivityScope
    fun presenter(router: SplashContract.Router) = SplashPresenter(router)
}