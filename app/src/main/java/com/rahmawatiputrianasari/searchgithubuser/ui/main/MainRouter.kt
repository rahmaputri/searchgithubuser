package com.rahmawatiputrianasari.searchgithubuser.ui.main

import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import com.rahmawatiputrianasari.searchgithubuser.ui.detail.DetailActivity
import com.rahmawatiputrianasari.searchgithubuser.ui.main.MainActivity
import com.rahmawatiputrianasari.searchgithubuser.ui.main.MainContract

class MainRouter(private val activity: MainActivity) : MainContract.Router {
    override fun finish() {
        activity.finish()
    }

    override fun openFullJoke(data: Joke) {
        DetailActivity.launch(activity, data)
    }
}