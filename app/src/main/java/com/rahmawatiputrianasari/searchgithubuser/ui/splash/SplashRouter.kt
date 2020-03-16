package com.rahmawatiputrianasari.searchgithubuser.ui.splash

import com.rahmawatiputrianasari.searchgithubuser.ui.main.MainActivity

class SplashRouter(private val activity: SplashActivity) : SplashContract.Router {

    override fun openMain() {
        MainActivity.launch(activity)
        activity.finish()
    }
}