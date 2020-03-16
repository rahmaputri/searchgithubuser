package com.rahmawatiputrianasari.searchgithubuser.ui.detail

import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import com.rahmawatiputrianasari.searchgithubuser.ui.main.MainActivity
import com.rahmawatiputrianasari.searchgithubuser.ui.main.MainContract

class DetailRouter(private val activity: DetailActivity) : DetailContract.Router {
    override fun finish() {
        activity.finish()
    }
}