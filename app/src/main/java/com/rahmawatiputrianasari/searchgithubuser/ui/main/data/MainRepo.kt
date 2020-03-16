package com.rahmawatiputrianasari.searchgithubuser.ui.main.data

import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import com.rahmawatiputrianasari.searchgithubuser.ui.main.MainContract
import com.rahmawatiputrianasari.searchgithubuser.ui.main.api.MainApi
import io.reactivex.Single

class MainRepo(private val api: MainApi) : MainContract.Repo {

    override fun getJokes(): Single<List<Joke>> = api.getData()
}