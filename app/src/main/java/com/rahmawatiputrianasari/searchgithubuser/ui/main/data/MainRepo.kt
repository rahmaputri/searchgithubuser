package com.rahmawatiputrianasari.searchgithubuser.ui.main.data

import com.rahmawatiputrianasari.searchgithubuser.app.model.SearchResponse
import com.rahmawatiputrianasari.searchgithubuser.ui.main.MainContract
import com.rahmawatiputrianasari.searchgithubuser.ui.main.api.MainApi
import io.reactivex.Single

class MainRepo(private val api: MainApi) :
    MainContract.Repo {

    override fun getUsers(username: String, page: String): Single<SearchResponse> =
        api.getUsers(username, page)
}