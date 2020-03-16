package com.rahmawatiputrianasari.searchgithubuser.ui.main


class MainRouter(private val activity: MainActivity) : MainContract.Router {
    override fun finish() {
        activity.finish()
    }
}